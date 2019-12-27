package com.doctorn.userAccount.userAccount;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.conversation.UserConservationActivity;
import com.doctorn.user.LoginActivity;
import com.doctorn.R;
import com.doctorn.conversation.DoctorConversationActivity;
import com.doctorn.models.User;
import com.doctorn.user.OurPageActivity;
import com.doctorn.user.SpecialtiesActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAccountActivity extends AppCompatActivity {

    private static final String TAG = UserAccountActivity.class.getSimpleName();
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.doctor_name_id) TextView doctorName;
    @BindView(R.id.doctor_img_id)
    CircleImageView circleImageView;

    @BindView(R.id.our_page_id)TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialitiesTab;
    @BindView(R.id.my_account_id)TextView myAccountTab;
    @BindView(R.id.conversation_id)TextView conservationTab;
    private static int PC_Picker=1;

   UserPagerAdapter adaper;
   public static  int flag;
   public static String x;
    User user;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        ButterKnife.bind(this);

        x="fragment";
       // flag=2;

        doctorName.setText(LoginActivity.user.getName());
          Intent intent=getIntent();

        if(intent.hasExtra("update_data")){
            User user=getIntent().getParcelableExtra("update_data");
            doctorName.setText(user.getName());
            // doctorName.setText(user.getName());
        }
       else if(intent.hasExtra("user_data")){
            User user=intent.getParcelableExtra("user_data");
            doctorName.setText(user.getName());
            flag=1;
            Log.v(TAG,"flag ss"+flag);
        }

        adaper=new UserPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adaper);
       tabLayout.setSelectedTabIndicatorHeight(0);

    }

    @OnClick(R.id.conversation_id)
    void conversationClick(){
        Intent intent=new Intent(UserAccountActivity.this, UserConservationActivity.class);
            startActivity(intent);


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

    @OnClick(R.id.our_page_id)
    void ourPageClick(){
        Intent intent=new Intent(UserAccountActivity.this, OurPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.specialties_id)
    void specialitiesClick(){
        Intent intent=new Intent(UserAccountActivity.this, SpecialtiesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,LoginActivity.class));
    }
}
