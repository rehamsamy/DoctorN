package com.doctorn.conversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.user.OurPageActivity;
import com.doctorn.user.SpecialtiesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctorConversationActivity extends AppCompatActivity {

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    ConversationAdapter adapter;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_conversation);
        ButterKnife.bind(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter=new ConversationAdapter(this);
            recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.my_account_id)
    void myAccountClick(){
            Intent intent=new Intent(DoctorConversationActivity.this, DoctorAccountActivity.class);
            startActivity(intent);

    }
}
