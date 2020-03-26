package com.doctorn;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG =SignUpActivity.class.getSimpleName() ;
    Animation up,down;
    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.user_name_input) EditText nameInput;
    @BindView(R.id.user_phone_input) EditText phoneInput;
    @BindView(R.id.user_email_input) EditText emailInput;
    @BindView(R.id.user_age_input) EditText ageInput;
    @BindView(R.id.user_type_input) TextView typeInput;
    @BindView(R.id.user_password_input) EditText passwordInput;
    @BindView(R.id.user_confirm_password_input) EditText confirmPasswordInput;
    @BindView(R.id.gender_spinner_id) Spinner spinner;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    @BindView(R.id.hello_msg) TextView helloMsg;
    @BindView(R.id.choose_photo_txt) TextView choosePhotoTxt;
    NetworkAvailable available;
    String [] gender={"male","female"};
    ArrayAdapter adapter;
    RetrofitInterface retrofitInterface;
    String gender_value;
    String registerPersonType;
   public static UserModel model;
   public static String token;
   DailogUtil dailogUtil;
   FirebaseAuth firebaseAuth;
   DatabaseReference reference;
    private static final int image_pick_gallery_code = 330;
    String storage_permission[];
    File file;
    MultipartBody.Part body;

    int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dailogUtil=new DailogUtil();
        //firebaseAuth=FirebaseAuth.getInstance();
        storage_permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
        available=new NetworkAvailable(this);
        firebaseAuth=FirebaseAuth.getInstance();




        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,gender);
        //spinner.setDrop
        spinner.setAdapter(adapter);


        Intent intent=getIntent();
        if(intent.getAction().equals("register_user")){
            registerPersonType="user";
            Log.v(TAG,"type"+registerPersonType);
            helloMsg.setText(getString(R.string.register_as_user));
            flag=1;

        }
        else if(intent.getAction().equals("register_doctor")){
            registerPersonType="doctor";
            Log.v(TAG,"type"+registerPersonType);
            choosePhotoTxt.setVisibility(View.VISIBLE);
            flag=2;
        }

    }

    @OnItemSelected(R.id.gender_spinner_id)
    void itemSelected(int index){
  gender_value=(String) adapter.getItem(index);
  typeInput.setText(gender_value);
        Log.v(TAG,"gender"+adapter.getItem(index));
    }


    @OnClick(R.id.view_password_id)
    void  viewPasswordClick(){
        passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

    }

    @OnClick(R.id.have_account_id)
    void loginClick(){
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
    }

    @OnClick(R.id.continu_btn)
    void continueRegister(){


        if (!FUtilsValidation.isEmpty(nameInput, getResources().getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(passwordInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(phoneInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(emailInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(ageInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(confirmPasswordInput, getString(R.string.edit_text_empty))
                && FUtilsValidation.isPasswordEqual(passwordInput,confirmPasswordInput,"password not equal??")) {
            if(available.isNetworkAvailable()){
                if(flag==1) {
                    registerUser();
                }else if(flag==2){
                    registerDoctor();
                }
            }else {
                Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_SHORT).show();
            }

        }


    }


    private void registerUser() {
        final ProgressDialog progressDialog=dailogUtil.showProgress(SignUpActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        final Map<String,Object> map=new HashMap<>();
        map.put("name",nameInput.getText().toString());
        map.put("user_phone",phoneInput.getText().toString());
        map.put("email",emailInput.getText().toString());
        map.put("user_age",ageInput.getText().toString());
        map.put("user_gender",typeInput.getText().toString());
        map.put("password",passwordInput.getText().toString());
        map.put("user_type",registerPersonType);
        map.put("password_confirmation",confirmPasswordInput.getText().toString());

        Log.v(TAG,"ggg"+typeInput.getText().toString());
        progressBar.setVisibility(View.GONE);
        Call<UserModel> call=retrofitInterface.registerUser(map);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                   if(registerPersonType.equals("user")){
                       model=response.body();
                       firebaseAuth.createUserWithEmailAndPassword(emailInput.getText().toString().trim(),passwordInput.getText().toString().trim())
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                     if(task.isSuccessful()){
                                       reference= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid());
                                         Intent intent=new Intent(SignUpActivity.this, DoctorListActivity.class);
                                         intent.putExtra("user_data",model);
                                         startActivity(intent);
                                     }else{
                                         Toast.makeText(SignUpActivity.this, task.getResult().toString(), Toast.LENGTH_LONG).show();
                                     }
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                           }
                       });

                       progressDialog.dismiss();

                   }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                       progressDialog.dismiss();
                    //Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


    }




    private void registerDoctor() {
        final ProgressDialog progressDialog=dailogUtil.showProgress(SignUpActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        final Map<String,Object> map=new HashMap<>();
        map.put("name",nameInput.getText().toString());
        map.put("user_phone",phoneInput.getText().toString());
        map.put("email",emailInput.getText().toString());
        map.put("user_age",ageInput.getText().toString());
        map.put("user_gender",typeInput.getText().toString());
        map.put("password",passwordInput.getText().toString());
        map.put("user_type",registerPersonType);
        map.put("password_confirmation",confirmPasswordInput.getText().toString());

        Log.v(TAG,"ggg"+typeInput.getText().toString());
        progressBar.setVisibility(View.GONE);
        Call<UserModel> call=retrofitInterface.registerDoctor(map,body);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                   if(registerPersonType.equals("doctor")){
                        model=response.body();
                        token=response.body().getToken();
                        firebaseAuth.createUserWithEmailAndPassword(emailInput.getText().toString().trim(),passwordInput.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            reference= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid());
                                            Intent intent=new Intent(SignUpActivity.this, ContinuRegisterActivity.class);
                                            intent.putExtra("data",model);
                                           // intent.putExtra("token",model.getToken());
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(SignUpActivity.this, task.getResult().toString(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        progressDialog.dismiss();
                    }

                }
                else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, response.body().getErrorModel().toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    //Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gender_value= (String) parent.getItemAtPosition(position);
        Log.v(TAG,"genderrr"+gender_value);
    }

    @OnClick(R.id.choose_photo_txt)
    void uploadImage(){
        try {
            if (ActivityCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SignUpActivity.this, storage_permission, image_pick_gallery_code);
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
                     createMltipart(file);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private  void createMltipart(File file){
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
        body=MultipartBody.Part.createFormData("doctor_photo","image.jpg",requestBody);
        choosePhotoTxt.setText(getString(R.string.photo_selected));

    }

}
