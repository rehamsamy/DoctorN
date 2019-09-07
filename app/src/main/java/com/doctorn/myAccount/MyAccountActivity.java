package com.doctorn.myAccount;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.doctorn.LoginActivity;
import com.doctorn.R;
import com.doctorn.conversation.ConversationActivity;
import com.doctorn.models.User;
import com.doctorn.myAccount.RegisterInfoFragemt.AccountRegisterInfoFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.doctor_name_id) TextView doctorName;
    @BindView(R.id.doctor_img_id)
    CircleImageView circleImageView;
    private static int PC_Picker=1;

    PagerAdaper adaper;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);

        adaper=new PagerAdaper(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adaper);
        user= LoginActivity.user;

        doctorName.setText(user.getName());

        if(getIntent().hasExtra("update_data")){
            User user=getIntent().getParcelableExtra("update_data");
            doctorName.setText(user.getName());
        }



    }

    @OnClick(R.id.conversation_id)
    void conversationClick(){
        startActivity(new Intent(MyAccountActivity.this, ConversationActivity.class));
    }


    @OnClick(R.id.doctor_img_id)
    void choosePhoto(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");

        startActivityForResult(Intent.createChooser(intent,"Choose Your Photo!"),PC_Picker);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==PC_Picker&&resultCode==RESULT_OK){
            Uri uri= data.getData();
            Picasso.get().load(uri).into(circleImageView);
        }
    }
}
