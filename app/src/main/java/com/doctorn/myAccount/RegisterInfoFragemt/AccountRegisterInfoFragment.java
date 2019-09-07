package com.doctorn.myAccount.RegisterInfoFragemt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.BalanceActivity;
import com.doctorn.ChangePasswordActivity;
import com.doctorn.EditProfileActivity;
import com.doctorn.LoginActivity;
import com.doctorn.R;
import com.doctorn.SettingActivity;
import com.doctorn.models.User;

import static android.support.constraint.Constraints.TAG;

public class AccountRegisterInfoFragment extends Fragment {

    ConstraintLayout balance;
    ConstraintLayout setting;
    ConstraintLayout changePassword, updateProfile;
    TextView email, phone, age, gender;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_register_info_fragment, container, false);
        balance = view.findViewById(R.id.cn1);
        setting = view.findViewById(R.id.cn2);
        email = view.findViewById(R.id.user_email_value_id);
        phone = view.findViewById(R.id.user_phone_value_id);
        age = view.findViewById(R.id.user_age_value_id);
        gender = view.findViewById(R.id.user_gender_value_id);
        changePassword = view.findViewById(R.id.cn4);
        updateProfile = view.findViewById(R.id.update_profile);


        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("update_data")) {
            Log.v(TAG,"ddddddddd"+intent.getParcelableExtra("update_data").toString());
            user = intent.getParcelableExtra("update_data");
            email.setText(user.getEmail());
            phone.setText(user.getUserPhone());
            Log.v(TAG,"user "+user.getName());
            age.setText(String.valueOf(user.getUserAge()));
            gender.setText(user.getUserGender());
        }

        else  {
            user = LoginActivity.user;
            email.setText(user.getEmail());
            phone.setText(user.getUserPhone());
            age.setText(String.valueOf(user.getUserAge()));
            gender.setText(user.getUserGender());
        }

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BalanceActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });


        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });


        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent=getActivity().getIntent();
//        if(intent.hasExtra("update_data")){
//            user=intent.getParcelableExtra("update_data");
//            email.setText(user.getEmail());
//            phone.setText(user.getUserPhone());
//            age.setText(String.valueOf(user.getUserAge()));
//            gender.setText(user.getUserGender());
//
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("update_data")) {
            Log.v(TAG,"ddddddddd"+intent.getParcelableExtra("update_data").toString());
            user = intent.getParcelableExtra("update_data");
            email.setText(user.getEmail());
            phone.setText(user.getUserPhone());
            age.setText(String.valueOf(user.getUserAge()));
            gender.setText(user.getUserGender());

        }
    }
}
