package com.doctorn;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doctorn.models.UserModel;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;
import com.fourhcode.forhutils.Futils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestionComplaintsActivity extends AppCompatActivity {

    @BindView(R.id.msg_title_id)
    EditText msgTitleInput;
    @BindView(R.id.msg_body_id)
    EditText msgBodyInput;
    @BindView(R.id.attatch_img_id)
    Button attatchImgBtn;
    RetrofitInterface retrofitInterface;
    private static final int PC_PICKER = 1;
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_complaints);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.attatch_img_id)
    void chooseImg() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), PC_PICKER);

    }


    @OnClick(R.id.send_btn_id)
    void sendComplaintsClick() {
        if (!FUtilsValidation.isEmpty(msgBodyInput, getResources().getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(msgTitleInput, getResources().getString(R.string.edit_text_empty))
                && !(inputStream == null)) {
            try {
                sendSuggestionsAndComplaints(getBytes(inputStream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendSuggestionsAndComplaints(byte[] bytes) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("title",msgTitleInput.getText().toString());
        map.put("message",msgBodyInput.getText().toString());
        map.put("user_id",LoginActivity.user.getId());
        map.put("api_token",LoginActivity.userModel.getToken());

        final RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),bytes);
        MultipartBody.Part part=MultipartBody.Part.createFormData("image","image.jpeg",requestBody);

         Call<UserModel> call=retrofitInterface.sendSuggestionAndComplaints(map,part);
         call.enqueue(new Callback<UserModel>() {
             @Override
             public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                 if(response.body().isStatus()){
                     Toast.makeText(SuggestionComplaintsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                      finish();
                 }else {

                     Toast.makeText(SuggestionComplaintsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                 }

             }

             @Override
             public void onFailure(Call<UserModel> call, Throwable t) {

             }
         });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PC_PICKER && resultCode == RESULT_OK) {
            try {
                inputStream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


    private byte[] getBytes(InputStream inputStream) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {

            outputStream.write(buffer);

        }
        return outputStream.toByteArray();
    }
}
