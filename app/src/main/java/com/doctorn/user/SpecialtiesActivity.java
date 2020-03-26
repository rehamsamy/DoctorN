package com.doctorn.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.conversation.DoctorConversationActivity;
import com.doctorn.conversation.UserConservationActivity;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.notification.NotificationsActivity;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialtiesActivity extends AppCompatActivity {

    @BindView(R.id.specialist_recycler_id) RecyclerView recyclerView;
    @BindView(R.id.progress_id)ProgressBar progressBar;
    @BindView(R.id.empty_list) TextView emptyListTxt;
    SpecialtiesAdapter adapter;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialties);
        ButterKnife.bind(this);

        getSpecialitiesData();




    }

   private void getSpecialitiesData() {
        Map<String,Object> map=new HashMap<>();
        map.put("page",1);
        map.put("limit",10);
        map.put("api_token",UserAccountActivity.user.getToken());
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        Call<SpecialtiesModel> call=retrofitInterface.getSpecialities(map);
        call.enqueue(new Callback<SpecialtiesModel>() {
            @Override
            public void onResponse(Call<SpecialtiesModel> call, Response<SpecialtiesModel> response) {
                if(response.body().isStatus()){
                    recyclerView.setLayoutManager(new LinearLayoutManager(SpecialtiesActivity.this));
                    adapter=new SpecialtiesAdapter(getApplicationContext(),response.body().getUserspecialties());
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    if(response.body().getUserspecialties().size()==0){
                        emptyListTxt.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                    }
                }else {
                    emptyListTxt.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SpecialtiesModel> call, Throwable t) {
                emptyListTxt.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.conversation_id)
    void conversationTab(){
        Intent intent=new Intent(SpecialtiesActivity.this, UserConservationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.our_page_id)
    void specialitiesClick(){
        Intent intent=new Intent(SpecialtiesActivity.this, OurPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_account_id)
    void myAccountClick(){
        Intent intent=new Intent(SpecialtiesActivity.this, UserAccountActivity.class);
        intent.setAction("specialist");
        startActivity(intent);
    }

    @OnClick(R.id.notificaion_img)
    void setNotificationImg(){
        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));

    }

}
