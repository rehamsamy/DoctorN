package com.doctorn.voiceChat;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.doctorn.R;
import com.doctorn.conversation.Message;
import com.doctorn.user.LoginActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class VoiceChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_recycler_id)
    RecyclerView recyclerView;
    @BindView(R.id.message_input)
    EditText inputMessage;
    @BindView(R.id.send_img_id)
    ImageView sendMsgImg;
    ChatAdapter adapter;
    List<Message> messageList=new ArrayList<>();
    Message message;
    int doctor_id=1;

    FirebaseDatabase mDatabase;
    DatabaseReference mMessageReference;
  public  static   String token="xhUJ8jDBJKXyXyNCh1Cr31EE9CzOcvsfGUHdun6aOSRIJ9lLszhVvIHNYzycj8jXUWc1euCFebeE6dHT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_chat);
        ButterKnife.bind(this);
        mDatabase=FirebaseDatabase.getInstance();
       // token=LoginActivity.userModel.getToken();
        mMessageReference=mDatabase.getReference().child("messages").child("send").child(token);
        readMessage(token,doctor_id);

        if (inputMessage.hasFocus()==true) {
            sendMsgImg.setImageResource(R.drawable.send);


        }
    }


    @OnClick(R.id.send_img_id)
    void sendMsgClick(){
       // message=new Message(inputMessage.getText().toString(), "Taha",String.valueOf(new Date()));
       // mMessageReference.push().setValue(message);
        sendMessage(token,1,inputMessage.getText().toString());
        inputMessage.setText("");
    }

    private void sendMessage(String token,int id,String mess){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> map=new HashMap<>();
        map.put("sender",token);
        map.put("receiver",id);
        map.put("message",mess);
        message =new Message(token,id,mess);
  databaseReference.child("chat").push().setValue(message);

    }

    private void readMessage(final String token, final int id){
        messageList=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("chat");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               messageList.clear();
              for(DataSnapshot data:dataSnapshot.getChildren()){
                  message=data.getValue(Message.class);
                if(message.getToken().equals(token)&& message.getId()==id){
                    messageList.add(message);
                   // Log.v("TAG","rrr"+message.getToken());
                }

              }
              adapter=new ChatAdapter(getApplicationContext(),messageList);
              recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
              recyclerView.setAdapter(adapter);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

    }


}
