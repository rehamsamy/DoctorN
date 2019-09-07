package com.doctorn.voiceChat;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.doctorn.R;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_chat);
        ButterKnife.bind(this);
        adapter = new ChatAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        if (inputMessage.hasFocus()==true) {
            sendMsgImg.setImageResource(R.drawable.send);


        }
    }


}
