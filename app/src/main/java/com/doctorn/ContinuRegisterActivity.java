package com.doctorn;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.models.LanguageModel;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.UserModel;
import com.doctorn.models.UserspecialtiesItem;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;
import com.fourhcode.forhutils.Futils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ContinuRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)
    ImageView imageView;
    @BindView(R.id.card_view)
    FrameLayout frameLayout;
    @BindView(R.id.graduate_university_input)
    EditText graduateUniversityInput;
    @BindView(R.id.graduation_year_input)
    EditText graduationYearInput;
   @BindView(R.id.specialist_spinner_id)
    Spinner specialistSpinner;
    @BindView(R.id.degree_input)
    EditText degreeInput;
     @BindView(R.id.profession_license)
     TextView professionLicenceInput;
    private static final int image_pick_gallery_code = 330;
    String storage_permission[];
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    Animation up, down;
    Dialog dialog;
    @BindView(R.id.accept_checkbox_id)
    CheckBox checkBox;
    Intent intent;
    UserModel userModel;
    String token;
    int doctor_id;
    File file;
    List<String> languages=new ArrayList<>();
    String [] languageNew;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String []languageSelected;
    @BindView(R.id.language_input) TextView languageInput;
    @BindView(R.id.specialist_value_id)TextView specialist;

    ArrayAdapter<String> specialistAdapter;
    List<String> specialtiesModelList=new ArrayList<>();
    private String specialValue;
    int specialist_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continu_register);
        ButterKnife.bind(this);
        up = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        down = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
        networkAvailable=new NetworkAvailable(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        storage_permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

        intent = getIntent();
        if (intent.hasExtra("data")&& LoginAsDoctorActivity.token!=null) {
            userModel = intent.getParcelableExtra("data");
             token=LoginAsDoctorActivity.token;
            Log.v("TAG","token is  "+token);
            doctor_id = userModel.getUser().getId();

        }else if(intent.hasExtra("data")&& SignUpActivity.token!=null){
            userModel = intent.getParcelableExtra("data");
            token=SignUpActivity.token;
            Log.v("TAG","token is  "+token);
            doctor_id = userModel.getUser().getId();
        }


        if(networkAvailable.isNetworkAvailable()){
            getLanguages();
            getSpecialist();
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }

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
                   Toast.makeText(ContinuRegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onFailure(Call<LanguageModel> call, Throwable t) {

           }
       });

    }

    @OnClick(R.id.continu_btn)
    void continuClick() {

        if (checkBox.isChecked()&& !FUtilsValidation.isEmpty(graduateUniversityInput,getString(R.string.edit_text_empty))
        && ! FUtilsValidation.isEmpty(graduationYearInput,getString(R.string.edit_text_empty))
        && ! FUtilsValidation.isEmpty(degreeInput,getString(R.string.edit_text_empty))
        && file !=null
        && mUserItems.size()!=0
        &&specialtiesModelList.size()!=0) {
            // registerDoctorInfo();

            dialog = new Dialog(this);
            dialog.setContentView(R.layout.confirmation_layout);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ConstraintLayout layout = dialog.findViewById(R.id.con_root);
            layout.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            Button button = dialog.findViewById(R.id.accept_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), FinalStepRegisterActivity.class);
                    intent.putExtra(Cons.graduation_universty, graduateUniversityInput.getText().toString());
                    intent.putExtra(Cons.graduation_year, graduationYearInput.getText().toString());
                    intent.putExtra(Cons.specialization, specialist_value);
                    intent.putExtra(Cons.degree, degreeInput.getText().toString());
                    intent.putExtra(Cons.profession_license,file);
                    intent.putExtra(Cons.languages, mUserItems);
                    intent.putExtra("token", token);
                    intent.putExtra("id", doctor_id);
                    intent.setAction(Cons.registerDoctorAction);

                    startActivity(intent);
                }
            });

        } else if(checkBox.isChecked()==false){
            Toast.makeText(getApplicationContext(), "please check the box to continue??", Toast.LENGTH_SHORT).show();
        }else if(mUserItems.size()==0){
            Toast.makeText(getApplicationContext(), getString(R.string.choose_language), Toast.LENGTH_SHORT).show();
        }else if(file==null){
            Toast.makeText(getApplicationContext(), getString(R.string.choose_photo), Toast.LENGTH_SHORT).show();
        }else if(specialtiesModelList.size()==0){
            Toast.makeText(getApplicationContext(), getString(R.string.choose_specialist), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.profession_license)
    void uploadImage() {

        Log.v("TAG","xxx");
       try {
            if (ActivityCompat.checkSelfPermission(ContinuRegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContinuRegisterActivity.this, storage_permission, image_pick_gallery_code);
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
            case image_pick_gallery_code:
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


    @OnClick(R.id.language_input)
    void selectLanguage(){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ContinuRegisterActivity.this);
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
                languageInput.setText(item);

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
                    languageInput.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }


    @OnItemSelected(R.id.specialist_spinner_id)
    void getSpeciaistList(int index){
        specialValue = (String) specialistAdapter.getItem(index);
        specialist.setText(specialValue);
        Log.v("TAG", "spinner" + index);
       specialist_value=index+1;
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
                            specialistSpinner.setAdapter(specialistAdapter);
                        } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                            specialtiesModelList.add(item.getSpecialtiesNameEn());
                            specialistAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
                            specialistSpinner.setAdapter(specialistAdapter);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<SpecialtiesModel> call, Throwable t) {
                Toast.makeText(ContinuRegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
