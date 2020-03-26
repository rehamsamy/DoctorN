package com.doctorn.conversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorAccount.Reservations.ReservationsActivity;
import com.doctorn.doctorList.DoctorDetailsActivity;
import com.doctorn.notification.NotificationsActivity;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.user.OurPageActivity;
import com.doctorn.user.SpecialtiesActivity;
import com.doctorn.voiceChat.FirebaseUser;
import com.doctorn.voiceChat.VoiceChatActivity;
import com.google.firebase.auth.FirebaseAuth;
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

public class DoctorConversationActivity extends AppCompatActivity implements ConversationAdapter.OnItemIterface{

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    ConversationAdapter adapter;
    DatabaseReference reference;
    List<Message> messageList=new ArrayList<>();
    List<FirebaseUser> mUsers=new ArrayList<>();
    List<String> userList=new ArrayList<>();
    int flag;
    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_conversation);
        ButterKnife.bind(this);

        final LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        reference= FirebaseDatabase.getInstance().getReference().child("chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    message=dataSnapshot1.getValue(Message.class);
                    if(message.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        userList.add(message.getReceiver());
                    }if(message.getReceiver().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        userList.add(message.getSender());
                    }
                }
                readChat();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
           // adapter=new ConversationAdapter(this);
            //recyclerView.setAdapter(adapter);

    }

    private void readChat() {
      reference=FirebaseDatabase.getInstance().getReference().child("users");
      reference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              mUsers.clear();

              for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                  FirebaseUser user=dataSnapshot1.getValue(FirebaseUser.class);
                  if(!FirebaseAuth.getInstance().getCurrentUser().equals(user.getId())&&user.getType().equals("user")){
                      mUsers.add(user);
                  }
              }

              readChatByUser();
              adapter=new ConversationAdapter(getApplicationContext(),mUsers,DoctorConversationActivity.this);
              recyclerView.setAdapter(adapter);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });


    }

    private void readChatByUser() {
        reference= FirebaseDatabase.getInstance().getReference().child("chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    message=dataSnapshot1.getValue(Message.class);
                    // if(user.getType().equals("user")) {
                    if(message.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        userList.add(message.getReceiver());
                        Log.v("TAG","sendddd1");
                        Log.v("TAG","senddd1   "+userList.size());
                    }if(message.getReceiver().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        userList.add(message.getReceiver());
                        Log.v("TAG","sendddd2");
                        Log.v("TAG","senddd2   "+userList.size());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @OnClick(R.id.my_account_id)
    void myAccountClick(){
            Intent intent=new Intent(DoctorConversationActivity.this, DoctorAccountActivity.class);
            startActivity(intent);
    }

    @OnClick(R.id.notificaion_img)
    void setNotificationImg(){
        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));

    }

    @Override
    public void onClick(int pos) {
        Intent intent=new Intent(getApplicationContext(), VoiceChatActivity.class);
        intent.putExtra(DoctorDetailsActivity.DOCTOR_ID,mUsers.get(pos).getId());
        Log.v("TAG","idddd click"+mUsers.get(pos).getId());
        startActivity(intent);
    }

    @OnClick(R.id.reservations_id)
    void reservationClick(){
        Intent intent=new Intent(getApplicationContext(), ReservationsActivity.class);
        startActivity(intent);
    }
}
