package com.doctorn.voiceChat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.conversation.Message;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorDetailsActivity;
import com.doctorn.notify.Client;
import com.doctorn.notify.Data;
import com.doctorn.notify.MyResponse;
import com.doctorn.notify.Sender;
import com.doctorn.notify.Token;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoiceChatActivity extends AppCompatActivity {

    public static  String USER_ID ="user_id";
    @BindView(R.id.chat_recycler_id)
    RecyclerView recyclerView;
    @BindView(R.id.message_input)
    EditText inputMessage;
    @BindView(R.id.send_img_id)
    ImageView sendMsgImg;
    @BindView(R.id.item_name_toolbar)
    TextView itemNameToolbar;
    ChatAdapter adapter;
    List<Message> messageList=new ArrayList<>();
    FirebaseUser firebaseUser;
    Boolean notify = false;
    private MediaRecorder myAudioRecorder;
    private String outputFile;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
   static int userId;
   static int doctorId;
   public  static String user_id;
    public  static String doctor_id;
    public static String sender,receiver;
    RetrofitInterface retrofitInterface;
    FirebaseAuth firebaseAuth;
    DatabaseReference usersReference;
    Message message;
    String fuser;
    int flag;
    Dialog mDailog;
    DailogUtil dailogUtil;

    public static String tokenSend,tokenReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_chat);
        ButterKnife.bind(this);
        mDailog=new Dialog(this);
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();

        retrofitInterface= Client.getRetrofit("https://fcm.googleapis.com/")
        .create(RetrofitInterface.class);
        dailogUtil=new DailogUtil();

        // https://fcm.googleapis.com


        Intent intent=getIntent();
        if(intent.hasExtra(DoctorDetailsActivity.DOCTOR_ID)) {
           // doctorId = intent.getStringExtra(DoctorDetailsActivity.DOCTOR_ID);
           fuser=intent.getStringExtra(DoctorDetailsActivity.DOCTOR_ID);
           usersReference=FirebaseDatabase.getInstance().getReference().child("users").child(fuser);
           usersReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
               firebaseUser=dataSnapshot.getValue(FirebaseUser.class);
               itemNameToolbar.setText(firebaseUser.getName());

               readMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),fuser);
                   updateTokenn(FirebaseInstanceId.getInstance().getToken());

               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });

        }else  if(intent.hasExtra(USER_ID)){
            doctorId = LoginAsDoctorActivity.userModel.getUser().getId();
            doctor_id = "doctor"+String.valueOf(doctorId);
           // userId=intent.getIntExtra(USER_ID,1);
            user_id="user13";
            doctor_id="doctor11";
            sender=doctor_id;
            receiver=user_id;
            tokenSend=DoctorAccountActivity.token;
            tokenReceive= DoctorDetailsActivity.token;

        }
        Log.v("TAG","senddddd"+sender);
        Log.v("TAG","receiveeee"+receiver);
       // readMessage();



        inputMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    sendMsgImg.setImageResource(R.drawable.send);
                    flag=1;
                }else {
                    flag=2;
                    Toast.makeText(getApplicationContext(), "bbbbbb", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(inputMessage.hasFocus()){
            Log.v("TAG","flag 1");
        }else {
            flag=2;
            Log.v("TAG","flag 2");
        }


    }

    private void readMessage(final String currentUser, final String fuser) {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setStackFromEnd(true);
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("chat")){
                    messageList.clear();
                    Log.v("TAG","vvv xxx"+reference.toString());
                    reference.child("chat").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                               for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                   message = dataSnapshot1.getValue(Message.class);
                                   Log.v("TAG","vvv xxx1"+message.getSender());
                                    if (message.getReceiver().equals(currentUser) && message.getSender().equals(fuser) ||
                                            message.getSender().equals(currentUser) && message.getReceiver().equals(fuser)) {
                                        messageList.add(message);
                                    }
                                }
                                adapter = new ChatAdapter(getApplicationContext(), messageList);
                                recyclerView.setAdapter(adapter);
                                }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else {
                    Toast.makeText(VoiceChatActivity.this, getString(R.string.empty_list), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public  void updateTokenn(String updateToken) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token = new Token(updateToken);
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    @OnClick(R.id.send_img_id)
    void sendMsgClick(){
        if(flag==1) {
            String message = inputMessage.getText().toString();
            notify = true;
            if (!message.equals("")) {
                sendMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(), fuser, message, String.valueOf(new Date()));

            }
            inputMessage.setText("");
        }else if(flag==2){
            showPopupForRecord();
            Log.v("TAG","bbbbb");
        }
    }


    private void sendMessage(String sender, final String receiver, final String message, String date) {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("chat");
        Map<String,String> map=new HashMap<>();
        map.put("sender",sender);
        map.put("receiver",receiver);
        map.put("message",message);
        map.put("date",date);

        reference.push().setValue(map);

       reference = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
            FirebaseUser firebaseUser=dataSnapshot.getValue(FirebaseUser.class);
            sendNotification(receiver,firebaseUser.getName(),message);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }




    private void sendNotification(final String receiver, final String date, final String message) {
        Log.v("TAG","beforeee"+receiver);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Tokens");
         Query query=reference.orderByKey().equalTo(receiver);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   // for(DataSnapshot dataa:dataSnapshot.getChildren()){
                    for(DataSnapshot dataa:dataSnapshot.getChildren()){
                        Log.v("TAG","afterrrr"+dataSnapshot.getRef().toString());
                        Token token=dataa.getValue(Token.class);
                       // if(token.equals(receiver)){
                        Log.v("TAG","messsssssssss"+dataSnapshot.getChildren().toString());
                        Data data=new Data(FirebaseAuth.getInstance().getCurrentUser().getUid(),message,"message from :  "+date,R.drawable.send,date);
                        Log.v("TAG","dataaaa"+receiver + message );
                        Sender sender=new Sender(data,token.getToken());
                        Call<MyResponse> call=retrofitInterface.sendNotification(sender);
                        call.enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                if (response.code() == 200) {
                                          Toast.makeText(VoiceChatActivity.this, "sucess", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(VoiceChatActivity.this, "failed", Toast.LENGTH_LONG).show();
                                }

                            }
                            @Override
                            public void onFailure(Call<MyResponse> call, Throwable t) {

                            }
                        });

                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


    }

    public static String updateToken(String updateToken){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Tokens");
        Token token=new Token(updateToken);
        reference.push().setValue(token);
        return updateToken;

    }




    private void showPopupForRecord() {
        mDailog.setContentView(R.layout.record_msg_popup_layout);
        mDailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDailog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final Button record=mDailog.findViewById(R.id.record);
        final Button stop=mDailog.findViewById(R.id.stop);
        final Button send=mDailog.findViewById(R.id.play);

        mDailog.show();


        stop.setEnabled(false);
        send.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        myAudioRecorder = new MediaRecorder();

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(VoiceChatActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
            Toast.makeText(this, "permited", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "den", Toast.LENGTH_LONG).show();
            myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            myAudioRecorder.setOutputFile(outputFile);
            Log.v("TAG","nnnn"+outputFile.toString());

        }

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                    // make something ...
                } catch (IOException ioe) {
                    // make something
                }
                record.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
              //  myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                send.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Audio Recorder successfully", Toast.LENGTH_LONG).show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    uploadSoound(outputFile);
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    // make something
                }
            }
        });
    }

    private void uploadSoound(String outputFile) {
        if(outputFile !=null){
            Uri uri=Uri.fromFile(new File(outputFile));
            final ProgressDialog progressDialog= dailogUtil.showProgress(VoiceChatActivity.this,getString(R.string.wait_loading),false);
            StorageReference reference=storageReference.child("sounds"+outputFile);
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(VoiceChatActivity.this, "Uploaded", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(VoiceChatActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                            .getTotalByteCount());
                           progressDialog.setMessage("Uploaded "+(int)progress+"%");
                }
            });
        }

    }


}




