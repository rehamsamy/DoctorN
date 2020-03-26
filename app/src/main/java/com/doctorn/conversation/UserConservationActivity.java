package com.doctorn.conversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doctorn.R;
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


public class UserConservationActivity extends AppCompatActivity implements ConversationAdapter.OnItemIterface{

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.our_page_id) TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialistTab;
    @BindView(R.id.notification_progress_id) ProgressBar progressBar;
    ConversationAdapter adapter;
    List<Message> messageList=new ArrayList<>();
    List<FirebaseUser> usersList=new ArrayList<>();
    private List<Message> newList=new ArrayList<>();
    Message message;
    int flag;
    DatabaseReference mRefernce;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_conservation);
        ButterKnife.bind(this);

        //readDataFromDatabase();
        Log.v("TAG","ddddd"+newList.size());
        //readChat(newList);

        getDoctorsListFromFirebase();


    }


    private void getDoctorsListFromFirebase() {
        progressBar.setVisibility(View.VISIBLE);
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot  dataSnapshot1:dataSnapshot.getChildren()){
                    user=dataSnapshot1.getValue(FirebaseUser.class);
                    firebaseAuth=FirebaseAuth.getInstance();
                    if(!firebaseAuth.getCurrentUser().getUid().equals(user.getId())&&user.getType().equals("doctor")){
                        usersList.add(user);

                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new ConversationAdapter(getApplicationContext(), usersList, UserConservationActivity.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void readDataFromDatabase() {
        mRefernce= FirebaseDatabase.getInstance().getReference().child("chat");
        newList=new ArrayList<>();
        messageList=new ArrayList<>();
        mRefernce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageList.clear();
                usersList=new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    message = data.getValue(Message.class);
                    if(message.getSender().equals(VoiceChatActivity.user_id)||message.getReceiver().equals(VoiceChatActivity.user_id)){
                        messageList.add(message);
                    }
                }

                readChat(messageList);

//                  Log.v("TAG","sssss"+messageList.size());
//                  for(int i=0;i<messageList.size();i++){
//                      Message message=messageList.get(i);
//                      if(message.getSender().equals(VoiceChatActivity.user_id)&&
//                              message.getReceiver().equals(VoiceChatActivity.doctor_id)||
//                              message.getReceiver().equals(VoiceChatActivity.user_id)&&
//                                      message.getSender().equals(VoiceChatActivity.doctor_id)) {
//                                       newList.add(message);
//
//                      }
//                  }
//               // Log.v("TAG","ssssddddd"+messageList.get(0).getReceiver());
//                usersList.add(newList.get(newList.size() - 1));
//                Log.v("TAG","ssss"+usersList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }

    private void readChat(List<Message> messageList) {
        usersList.clear();
        newList.clear();
        for(int i=0;i<messageList.size();i++){
            Message message=messageList.get(i);
            if(message.getSender().equals(VoiceChatActivity.user_id)&&
                    message.getReceiver().equals(VoiceChatActivity.doctor_id)||
                    message.getReceiver().equals(VoiceChatActivity.user_id)&&
                            message.getSender().equals(VoiceChatActivity.doctor_id)){
                newList.add(message);
                flag=1;
                // usersList.add(newList.get(newList.size()-1));
                Log.v("TAG","dddd true");
            }else if(message.getSender().equals(VoiceChatActivity.user_id)&&
                    !message.getReceiver().equals(VoiceChatActivity.doctor_id)||
                    message.getReceiver().equals(VoiceChatActivity.user_id)&&
                            !message.getSender().equals(VoiceChatActivity.doctor_id)){
                newList.add(message);
                Log.v("TAG","dddd  false");
                flag=2;
                // usersList.add(newList.get(newList.size()-1));
            }
        }

        if(flag==1){
            newList.add(newList.get(newList.size()-1));
            Log.v("TAG","hhh"+usersList.size());
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter=new ConversationAdapter(getApplicationContext(),usersList,UserConservationActivity.this);
            recyclerView.setAdapter(adapter);
        }else if(flag==2){
            // usersList.add(newList.get(newList.size()-1));
            Log.v("TAG","hhh"+usersList.size());
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            //adapter=new ConversationAdapter(getApplicationContext(),newList,UserConservationActivity.this);
            recyclerView.setAdapter(adapter);
        }


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
        Intent intent=new Intent(getApplicationContext(), VoiceChatActivity.class);
        intent.putExtra(DoctorDetailsActivity.DOCTOR_ID,usersList.get(pos).getId());
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
