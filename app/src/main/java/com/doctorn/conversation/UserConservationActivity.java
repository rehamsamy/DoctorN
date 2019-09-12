package com.doctorn.conversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.user.OurPageActivity;
import com.doctorn.user.SpecialtiesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserConservationActivity extends AppCompatActivity {

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.our_page_id) TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialistTab;
    ConversationAdapter adapter;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_conservation);
        ButterKnife.bind(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ConversationAdapter(this);
        recyclerView.setAdapter(adapter);



    }


    @OnClick(R.id.our_page_id)
    void ourPageTab(){
        Intent intent=new Intent(getApplicationContext(), OurPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.specialties_id)
    void specialitiesClick(){
        Intent intent=new Intent(getApplicationContext(), SpecialtiesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_account_id)
    void myAccountClick(){

        Intent intent=new Intent(getApplicationContext(), UserAccountActivity.class);
        startActivity(intent);


    }
}
