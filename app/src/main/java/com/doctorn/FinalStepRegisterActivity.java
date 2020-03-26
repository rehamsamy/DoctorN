package com.doctorn;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.models.RegisterDoctorModel;
import com.doctorn.models.UserModel;
import com.doctorn.models.WorkDaysModel;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.MultiSelectionSpinner;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalStepRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.work_days_input)
    TextView workDaysInput;
    @BindView(R.id.counseling_time_input)EditText consultTimeInput;
    @BindView(R.id.price_input)EditText consultPriceInput;
    @BindView(R.id.work_time_start) EditText workTimeStart;
    @BindView(R.id.work_time_end) EditText workTimeEnd;

    @BindView(R.id.work_days_spinner)
    MultiSelectSpinner workdaysSpinner;
    String [] workdays;
    MultipartBody.Part body ;
    List<String> workdaysList=new ArrayList<>();
    RetrofitInterface retrofitInterface;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String [] workdaysSelected;
    List<Integer> langList=new ArrayList<>();
    List<Integer> oldLangList=new ArrayList<>();
    int doctor_id;

    File file;
   int specialist_value;
   DailogUtil dailogUtil;


    Intent intent;
    String graduation_university,graduation_year,degree,special,profession,language;
    Animation up,down;
    String token;
    NetworkAvailable networkAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_step_register);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);
        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        dailogUtil=new DailogUtil();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);

        if(networkAvailable.isNetworkAvailable()){
            getDoctorWorkingDays();
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }



        intent=getIntent();

        if(intent.hasExtra("token")){
            graduation_university=intent.getStringExtra(Cons.graduation_universty);
            graduation_year=intent.getStringExtra(Cons.graduation_year);
            degree=intent.getStringExtra(Cons.degree);
            //file=intent.getExtras.(Cons.profession_license);
            file=(File) intent.getExtras().get(Cons.profession_license);
            specialist_value=intent.getIntExtra(Cons.specialization,0);
            langList=intent.getIntegerArrayListExtra(Cons.languages);
            token=intent.getStringExtra("token");
            doctor_id=intent.getIntExtra("id",1);
            Log.v("TAG","file is "+ file+"  special "+specialist_value +" langaua "+langList);


            createMltipart(file);

           if(langList.size()==2){
               if(langList.get(0)==0&& langList.get(1)==1){
                   langList.clear();
                   langList.add(0,1);
                   langList.add(1,2);
               }
           }else if(langList.size()==1){
               if(langList.get(0)==0){
                   langList.clear();
                   langList.add(0,1);
               }else if(langList.get(0)==1){
                   langList.clear();
                   langList.add(0,2);
               }
           }
            Log.v("TAG","file is "+ file+"  special "+specialist_value +" langaua "+langList);


            // oldLangList=(List<Integer>)language;


        }

    }

    private void getDoctorWorkingDays() {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Call<WorkDaysModel> call=retrofitInterface.getWorksDayByDoctorId();
        call.enqueue(new Callback<WorkDaysModel>() {
            @Override
            public void onResponse(Call<WorkDaysModel> call, Response<WorkDaysModel> response) {
                if(response.body().isStatus()){
                    WorkDaysModel model=response.body();
                    for(int i=0;i<model.getDaysArrayModel().size();i++){
                        if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
                            workdaysList.add(model.getDaysArrayModel().get(i).getDayArName());
                        }else if(PreferenceHelper.getValue(getApplicationContext()).equals("en")){
                            workdaysList.add(model.getDaysArrayModel().get(i).getDayEnName());
                        }
                    }
                      workdays=new String[workdaysList.size()];

                    for(int i=0;i<workdaysList.size();i++){

                       workdays[i]= workdaysList.get(i);
                    }
                    checkedItems = new boolean[workdays.length];
                    workDaysInput.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            putWorkdays(workdays,checkedItems);
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<WorkDaysModel> call, Throwable t) {

            }
        });


    }



    @OnClick(R.id.create_account_btn)
    void createAccountClick(){

        registerDoctorInfo();
    }


    private void registerDoctorInfo() {
        final ProgressDialog progressDialog=dailogUtil.showProgress(FinalStepRegisterActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("graduation_universty",graduation_university);
        map.put("graduation_year",graduation_year);
        map.put("specialization_id",specialist_value);
        map.put("degree",degree);
        map.put("languages",langList);
        map.put("work_days",mUserItems);
        map.put("consultation_duration",consultTimeInput.getText().toString());
        map.put("consultation_price",consultPriceInput.getText().toString());
        map.put("work_start_time",workTimeStart.getText().toString());
        map.put("work_end_time",workTimeEnd.getText().toString());
        map.put("Account_Activation","Active");
        map.put("doctor_id",doctor_id);
        map.put("api_token",token);
        Log.v("TAG","token is  "+token);
        map.put("lang",PreferenceHelper.getValue(getApplicationContext()));
        retrofit2.Call<RegisterDoctorModel> call=retrofitInterface.addOrEditDoctorInfo(map,body);
        call.enqueue(new Callback<RegisterDoctorModel>() {
            @Override
            public void onResponse(retrofit2.Call<RegisterDoctorModel> call, Response<RegisterDoctorModel> response) {
                if(response.body().isStatus()){
                    Intent intent=new Intent(getApplicationContext(), DoctorAccountActivity.class);
                    intent.putExtra("token",token);
                    intent.putExtra("doctor_id",response.body().getDoctorinformation().getDoctorId());
                    startActivity(intent);
                    progressDialog.dismiss();
                    Toast.makeText(FinalStepRegisterActivity.this,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(FinalStepRegisterActivity.this,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RegisterDoctorModel> call, Throwable t) {
                Toast.makeText(FinalStepRegisterActivity.this,t.getMessage().toString(), Toast.LENGTH_LONG).show();
           progressDialog.dismiss();
            }
        });
    }




    private void putWorkdays(final String [] workdays, final boolean [] checkedItems) {
        //checkedItems = new boolean[workdaysList.length];
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(FinalStepRegisterActivity.this);
        mBuilder.setTitle(R.string.choose_workdays);
        mBuilder.setMultiChoiceItems(workdays, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    mUserItems.add(position);
                }else{
                    mUserItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                workdaysSelected=new String[mUserItems.size()];
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + workdays[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }

                    workdaysSelected[i]=workdays[mUserItems.get(i)];
                }
                workDaysInput.setText(item);

                //for(int i=0;i<mUserItems.size();i++){
                    Log.v("TAG" ,"item "+"  "+mUserItems.toString());
                //}
            }
        });

        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mUserItems.clear();
                    workDaysInput.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private  void createMltipart(File file){
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
        body=MultipartBody.Part.createFormData("profession_license","image.jpg",requestBody);

    }
}
