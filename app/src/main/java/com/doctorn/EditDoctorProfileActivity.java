package com.doctorn;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.AllDoctorsModel;
import com.doctorn.models.DoctorInfoModel;
import com.doctorn.models.DoctorItemModel;
import com.doctorn.models.LanguageModel;
import com.doctorn.models.RegisterDoctorModel;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.UserspecialtiesItem;
import com.doctorn.models.WorkDaysModel;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDoctorProfileActivity extends AppCompatActivity {

    @BindView(R.id.graduate_university_input) EditText graduateUniversityInput;
    @BindView(R.id.graduation_year_input) EditText graduateYearInput;
    @BindView(R.id.degree_input) EditText degreeInput;
    @BindView(R.id.specialist_spinner_id) Spinner spinnerId;
    @BindView(R.id.specialist_value_id) TextView specialistValueTxt;
    @BindView(R.id.language_input) TextView languageTxt;
    @BindView(R.id.profession_license) TextView professionLicenceTxt;
    @BindView(R.id.work_days_input) TextView workdaysTxt;
    @BindView(R.id.work_time_start) EditText startTimeInput;
    @BindView(R.id.work_time_end) EditText endTimeInput;
    @BindView(R.id.counseling_time_input) EditText counselingTimeInput;
    @BindView(R.id.price_input)EditText priceInput;
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    private int image_pick_gallery_code=1;
    String []languageSelected;
    String storage_permission[];
    File file;
    MultipartBody.Part body;
    DailogUtil dailogUtil;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String [] workdaysSelected;
    List<Integer> langList=new ArrayList<>();
    String [] workdays;
    List<String> workdaysList=new ArrayList<>();
    boolean[] checkedItems;
    ArrayAdapter<String> specialistAdapter;
    List<String> specialtiesModelList=new ArrayList<>();
    private String specialValue;
    int specialist_value;

    List<String> languages=new ArrayList<>();
    String [] languageNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_profile);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);
        dailogUtil=new DailogUtil();
        storage_permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if(networkAvailable.isNetworkAvailable()) {
            getDoctorInfo();
            getDoctorWorkingDays();
            getSpecialist();
            getLanguages();

//            for(int i=0;i<mUserItems.size();i++){
//                langList.add(mUserItems.get(i));
//            }


        }
        else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }
    }



    @OnClick(R.id.edit_btn_id)
    void editProfile(){
   if(networkAvailable.isNetworkAvailable()){
       if (!FUtilsValidation.isEmpty(graduateUniversityInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(graduateYearInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(degreeInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(counselingTimeInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(priceInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(startTimeInput, getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(endTimeInput, getString(R.string.edit_text_empty))){
           createMltipart(file);
           editDoctorData();
       } else if(mUserItems.size()==0){
           Toast.makeText(getApplicationContext(), getString(R.string.choose_language), Toast.LENGTH_SHORT).show();
       }else if(file==null){
           Toast.makeText(getApplicationContext(), getString(R.string.choose_photo), Toast.LENGTH_SHORT).show();
       }else if(specialtiesModelList.size()==0){
           Toast.makeText(getApplicationContext(), getString(R.string.choose_specialist), Toast.LENGTH_SHORT).show();
       }

   }else {
       Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
   }
    }

    private void editDoctorData() {
        final ProgressDialog progressDialog=dailogUtil.showProgress(EditDoctorProfileActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("graduation_universty",graduateUniversityInput.getText().toString());
        map.put("graduation_year",graduateYearInput.getText().toString());
        map.put("specialization_id",specialist_value);
        map.put("degree",degreeInput.getText().toString());
        map.put("languages",langList);
        map.put("work_days",mUserItems);
        Log.v("TAG","item "+mUserItems.toString());
        map.put("consultation_duration",counselingTimeInput.getText().toString());
        map.put("consultation_price",priceInput.getText().toString());
        map.put("work_start_time",startTimeInput.getText().toString());
        map.put("work_end_time",endTimeInput.getText().toString());
        map.put("Account_Activation","Active");
        map.put("doctor_id", DoctorAccountActivity.user.getUser().getId());
        map.put("api_token",DoctorAccountActivity.user.getToken());
        map.put("lang", PreferenceHelper.getValue(getApplicationContext()));
        retrofit2.Call<RegisterDoctorModel> call=retrofitInterface.addOrEditDoctorInfo(map,body);

        call.enqueue(new Callback<RegisterDoctorModel>() {
            @Override
            public void onResponse(retrofit2.Call<RegisterDoctorModel> call, Response<RegisterDoctorModel> response) {
                if(response.body().isStatus()){
                    progressDialog.dismiss();
//                    Intent intent=new Intent(getApplicationContext(), DoctorAccountActivity.class);
//                    intent.putExtra("token",token);
//                    intent.putExtra("doctor_id",response.body().getDoctorinformation().getDoctorId());
//                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RegisterDoctorModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage().toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }



    @OnClick(R.id.profession_license)
    void uploadImage() {
        Log.v("TAG","xxx");
        try {
            if (ActivityCompat.checkSelfPermission(EditDoctorProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditDoctorProfileActivity.this, storage_permission, image_pick_gallery_code);
                Log.v("TAG","xxx1");
            } else {
                pickGallery();
                Log.v("TAG","xxx2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("TAG","xxx"+e.getMessage());
        }

    }

    private void pickGallery() {
        Intent gallery_intent = new Intent(Intent.ACTION_PICK);
        gallery_intent.setType("image/*");
        startActivityForResult(gallery_intent, image_pick_gallery_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickGallery();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();

                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == image_pick_gallery_code) {
                try {
                    Uri selectedImage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    InputStream is = getContentResolver().openInputStream(data.getData());
                    String [] proj={MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery( data.getData(),
                            proj, // Which columns to return
                            null,       // WHERE clause; which rows to return (all rows)
                            null,       // WHERE clause selection arguments (none)
                            null); // Order-by clause (ascending by name)
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    file=new File(cursor.getString(column_index));
                    // createMultiPartFile(file);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private  void createMltipart(File file){
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
        body= MultipartBody.Part.createFormData("profession_license","image.jpg",requestBody);

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
                    workdaysTxt.setOnClickListener(new View.OnClickListener() {
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


    private void putWorkdays(final String [] workdays, final boolean [] checkedItems) {
        //checkedItems = new boolean[workdaysList.length];
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditDoctorProfileActivity.this);
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
                workdaysTxt.setText(item);

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
                    workdaysTxt.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }



    private void getSpecialist() {
        final Map<String, Object> map = new HashMap<>();
        map.put("lang", PreferenceHelper.getValue(getApplicationContext()));
        map.put("api_token", "e5vnSkAqnP9jZYAvP8QrHC0V2FQOfhv0x9pTMA5GmN5lBCsTEF4D56w9XM2A7GIaO7O3oM1vbsYvmDRi");
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Call<SpecialtiesModel> call = retrofitInterface.getSpecialities(map);
        call.enqueue(new Callback<SpecialtiesModel>() {
            @Override
            public void onResponse(Call<SpecialtiesModel> call, Response<SpecialtiesModel> response) {
                if (response.body().isStatus()) {
                    for (int i = 0; i < response.body().getUserspecialties().size(); i++) {
                        UserspecialtiesItem item = response.body().getUserspecialties().get(i);
                        if (PreferenceHelper.getValue(getApplicationContext()).equals("ar")) {
                            specialtiesModelList.add(item.getSpecialtiesNameAr());
                            //Log.v(TAG, "value    " + specialtiesModelList.size());
                            specialistAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
                            spinnerId.setAdapter(specialistAdapter);
                        } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                            specialtiesModelList.add(item.getSpecialtiesNameEn());
                            specialistAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
                            spinnerId.setAdapter(specialistAdapter);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<SpecialtiesModel> call, Throwable t) {
                Toast.makeText(EditDoctorProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



    private void getLanguages() {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Call<LanguageModel> call= retrofitInterface.getlangauge();
        call.enqueue(new Callback<LanguageModel>() {
            @Override
            public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                if(response.body().isStatus()){
                    for(int i=0;i<response.body().getLanguageModelList().size();i++){
                        if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
                            languages.add(response.body().getLanguageModelList().get(i).getLanguageArName());
                        }else if(PreferenceHelper.getValue(getApplicationContext()).equals("en")){
                            languages.add(response.body().getLanguageModelList().get(i).getLanguageEnName());
                        }
                    }
                    languageNew=new String[languages.size()];
                    for(int i=0;i<languages.size();i++){
                        languageNew[i]=languages.get(i);
                    }

                    checkedItems = new boolean[languageNew.length];


                }else {
                    Toast.makeText(EditDoctorProfileActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LanguageModel> call, Throwable t) {

            }
        });

    }

    @OnItemSelected(R.id.specialist_spinner_id)
    void getSpeciaistList(int index){
        specialValue = (String) specialistAdapter.getItem(index);
        specialistValueTxt.setText(specialValue);
        Log.v("TAG", "spinner" + index);
        specialist_value=index+1;
    }



    @OnClick(R.id.language_input)
    void selectLanguage(){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditDoctorProfileActivity.this);
        mBuilder.setTitle(R.string.choose_workdays);
        mBuilder.setMultiChoiceItems(languageNew, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
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
                languageSelected=new String[mUserItems.size()];
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + languageNew[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }

                    languageSelected[i]=languageNew[mUserItems.get(i)];
                }
                languageTxt.setText(item);

                //for(int i=0;i<mUserItems.size();i++){
                Log.v("TAG" ,"item "+"  "+mUserItems.toString());
                //}


                if(mUserItems.size()==2){
                    if(mUserItems.get(0)==0&& mUserItems.get(1)==1){
                        mUserItems.clear();
                        mUserItems.add(0,1);
                        mUserItems.add(1,2);
                    }
                }else if(mUserItems.size()==1){
                    if(mUserItems.get(0)==0){
                        mUserItems.clear();
                        mUserItems.add(0,1);
                    }else if(mUserItems.get(0)==1){
                        mUserItems.clear();
                        mUserItems.add(0,2);
                    }
                }

                Log.v("TAG" ,"item "+"  "+mUserItems.toString());


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
                    languageTxt.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }


    private void getDoctorInfo() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<AllDoctorsModel> call = retrofitInterface.getDoctorInfoDetails(
                DoctorAccountActivity.user.getToken()
                , DoctorAccountActivity.user.getUser().getId(),PreferenceHelper.getValue(getApplicationContext()));

        call.enqueue(new Callback<AllDoctorsModel>() {
            @Override
            public void onResponse(Call<AllDoctorsModel> call, Response<AllDoctorsModel> response) {
                if(response.body().isStatus()){
                    DoctorInfoModel model=response.body().getDoctorInfoModel();
                    graduateUniversityInput.setText(response.body().getDoctorInfoModel().getGraduationUniversty());
                    graduateYearInput.setText(model.getGraduationYear());
                    startTimeInput.setText(model.getWorkStartTime());
                    endTimeInput.setText(model.getWorkEndTime());
                    counselingTimeInput.setText(String.valueOf(model.getConsultationDuration()));
                    priceInput.setText(String.valueOf(model.getConsultationDuration()));
                    degreeInput.setText(model.getDegree());

                    if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
                        specialistValueTxt.setText(model.getSpecializationArName());
                        //specialistTxt2.setText(model.getSpecializationArName());
                        for(String x:response.body().getDoctorInfoModel().getLanguagesAr()){
                            languageTxt.setText(x+" , ");
                        }
                        for(String x:response.body().getDoctorInfoModel().getWorkdaysAr()){
                            workdaysTxt.setText(x+" , ");
                        }
                    } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                        specialistValueTxt.setText(model.getSpecializationEnName());
                        //specialistTxt2.setText(model.getSpecializationEnName());
                        for(String x:response.body().getDoctorInfoModel().getLanguagesEn()){
                            languageTxt.setText(x+" , ");
                        }
                        for(String x:response.body().getDoctorInfoModel().getWorkdaysEn()){
                            workdaysTxt.setText(x+" , ");
                        }

                    }
                }else {
                    // Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllDoctorsModel> call, Throwable t) {
                // Toast.makeText(DoctorDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
