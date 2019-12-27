package com.doctorn.conversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.notification.NotificationsActivity;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.user.OurPageActivity;
import com.doctorn.user.SpecialtiesActivity;
import com.doctorn.voiceChat.VoiceChatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.doctorn.voiceChat.VoiceChatActivity.token;

public class UserConservationActivity extends AppCompatActivity implements ConversationAdapter.OnItemIterface{

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.our_page_id) TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialistTab;
    ConversationAdapter adapter;
    List<Message> messageList=new ArrayList<>();
    Message message;

    DatabaseReference mRefernce;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_conservation);
        ButterKnife.bind(this);

        mRefernce= FirebaseDatabase.getInstance().getReference().child("chat");
        mRefernce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                  message=  data.getValue(Message.class);
                  if(message.getToken().equals(token)||message.getId()==1){
                     // messageList.add(message);
                  }else if(message.getId()==1){

                  }
                }
                messageList.add(message);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new ConversationAdapter(getApplicationContext(),messageList,UserConservationActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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

    @OnClick(R.id.notificaion_img)
    void setNotificationImg(){
        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));

    }

    @Override
    public void onClick(int pos) {
        startActivity(new Intent(getApplicationContext(), VoiceChatActivity.class));
    }
}
