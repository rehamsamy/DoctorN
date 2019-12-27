package com.doctorn.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.models.NotificationDataArrayModel;
import com.doctorn.models.NotificationModel;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

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
    NetworkAvailable networkAvailable;
    List<NotificationDataArrayModel> notificationModels=new ArrayList<>();
    int current_page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);
        if (networkAvailable.isNetworkAvailable()) {
            buildRecyclerForNotification();
            getNotifications(current_page);
        } else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }

    }

    private void buildRecyclerForNotification() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        adapter=new NotificationAdapter(NotificationsActivity.this,notificationModels);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getNotifications(current_page);
            }
        });

    }

    private void getNotifications(final int current_page) {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        retrofit2.Call<NotificationModel> call=retrofitInterface.getNotifications(LoginActivity.userModel.getToken(),
                "yes",current_page,10);

        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(retrofit2.Call<NotificationModel> call, Response<NotificationModel> response) {
                if(response.body().isStatus()){
                    if(response.body().getNotifications().getNotificationDataArrayModels().size()>0){
                        notificationModels.addAll(response.body().getNotifications().getNotificationDataArrayModels());
                        adapter.notifyDataSetChanged();
                        Log.v("TAG","rrrrr"+response.body().getNotifications().getNotificationDataArrayModels().size());
                        progressBar.setVisibility(View.GONE);
                    }else if(response.body().getNotifications().getNotificationDataArrayModels().size()==0&&current_page==1){
                        progressBar.setVisibility(View.GONE);
                        emptyList.setVisibility(View.VISIBLE);
                    }
               }else {
                    progressBar.setVisibility(View.GONE);
                    emptyList.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
               }
            }

            @Override
            public void onFailure(retrofit2.Call<NotificationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });

    }
}
