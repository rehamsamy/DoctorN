package com.doctorn.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.doctorn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.notification_recycler_id)RecyclerView recyclerView;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        adapter=new NotificationAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
