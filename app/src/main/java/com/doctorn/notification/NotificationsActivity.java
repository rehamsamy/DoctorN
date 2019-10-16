package com.doctorn.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.notification_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.notification_progress_id) ProgressBar progressBar;
    @BindView(R.id.empty_list) TextView emptyList;
    NotificationAdapter adapter;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        getNotifications();
    }

    private void getNotifications() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        retrofit2.Call<UserModel> call=retrofitInterface.getGeneralNotification("9hduuiogCPiGS7IPr2gri6gXYwyhqmi7xd6GtSKSIzmMsLD4bLL89SrfYB82mPcQ6Z9SbB5D7YtcfXcj");
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()&&response.body().getNotificationModel().size()>=0){
                    adapter=new NotificationAdapter(getApplicationContext(),response.body().getNotificationModel());
                    recyclerView.setLayoutManager(new LinearLayoutManager(NotificationsActivity.this));
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }else if(response.body().isStatus()&&response.body().getNotificationModel().size()<0){
                    progressBar.setVisibility(View.GONE);
                    emptyList.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });

    }
}
