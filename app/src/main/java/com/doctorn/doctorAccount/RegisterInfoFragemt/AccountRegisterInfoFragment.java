package com.doctorn.doctorAccount.RegisterInfoFragemt;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.BalanceActivity;
import com.doctorn.ChangePasswordActivity;
import com.doctorn.EditProfileActivity;
import com.doctorn.R;
import com.doctorn.SettingActivity;
import com.doctorn.models.User;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class AccountRegisterInfoFragment extends Fragment {

    ConstraintLayout balance,logout;
    ConstraintLayout setting;
    ConstraintLayout changePassword, updateProfile;
    TextView email, phone, age, gender;
    ImageView arrowEngImg,arrowArImg,arrowAr1,arrowAr2,arrowAr3,arrowAr4,arrowEng1,arrowEng2,arrowEng3,arrowEng4;
    User user;
    RetrofitInterface retrofitInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_register_info_fragment, container, false);
        balance = view.findViewById(R.id.cn4);
        setting = view.findViewById(R.id.cn2);
        logout=view.findViewById(R.id.cn3);
        email = view.findViewById(R.id.user_email_value_id);
        phone = view.findViewById(R.id.user_phone_value_id);
        age = view.findViewById(R.id.user_age_value_id);
        gender = view.findViewById(R.id.user_gender_value_id);
        changePassword = view.findViewById(R.id.cn1);
        updateProfile = view.findViewById(R.id.update_profile);
        arrowEngImg=view.findViewById(R.id.arrow_eng);
        arrowArImg=view.findViewById(R.id.arrow_ar);
        arrowAr1=view.findViewById(R.id.arrow_ar1);
        arrowAr2=view.findViewById(R.id.arrow_ar2);
        arrowAr3=view.findViewById(R.id.arrow_ar3);
        arrowAr4=view.findViewById(R.id.arrow_ar4);
        arrowEng1=view.findViewById(R.id.arrow_eng1);
        arrowEng2=view.findViewById(R.id.arrow_eng2);
        arrowEng3=view.findViewById(R.id.arrow_eng3);
        arrowEng4=view.findViewById(R.id.arrow_eng4);


        if(PreferenceHelper.getValue(getContext()).equals("en")){
            arrowEngImg.setVisibility(View.VISIBLE);
            arrowEng1.setVisibility(View.VISIBLE);
            arrowEng2.setVisibility(View.VISIBLE);
            arrowEng3.setVisibility(View.VISIBLE);
            arrowEng4.setVisibility(View.VISIBLE);
            arrowArImg.setVisibility(View.GONE);
            arrowAr1.setVisibility(View.GONE);
            arrowAr2.setVisibility(View.GONE);
            arrowAr3.setVisibility(View.GONE);
            arrowAr4.setVisibility(View.GONE);
        }else if(PreferenceHelper.getValue(getContext()).equals("ar")){
            arrowEngImg.setVisibility(View.GONE);
            arrowEng1.setVisibility(View.GONE);
            arrowEng2.setVisibility(View.GONE);
            arrowEng3.setVisibility(View.GONE);
            arrowEng4.setVisibility(View.GONE);
            arrowArImg.setVisibility(View.VISIBLE);
            arrowAr1.setVisibility(View.VISIBLE);
            arrowAr2.setVisibility(View.VISIBLE);
            arrowAr3.setVisibility(View.VISIBLE);
            arrowAr4.setVisibility(View.VISIBLE);
        }


        final Intent intent = getActivity().getIntent();
        if (intent.hasExtra("update_data")) {
            Log.v(TAG,"ddddddddd"+intent.getParcelableExtra("update_data").toString());
            user = intent.getParcelableExtra("update_data");
            email.setText(user.getEmail());
            phone.setText(user.getUserPhone());
            Log.v(TAG,"user "+user.getName());
            age.setText(String.valueOf(user.getUserAge()));
            gender.setText(user.getUserGender());
        }

        else if(intent.hasExtra("doctor_data")){
             user=intent.getParcelableExtra("doctor_data");
            email.setText(user.getEmail());
            phone.setText(user.getUserPhone());
            age.setText(String.valueOf(user.getUserAge()));
            gender.setText(user.getUserGender());


        }else if(intent.hasExtra("user_data")){
             user=intent.getParcelableExtra("user_data");
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
                Intent intent1=new Intent(getContext(), SettingActivity.class);
                if(intent.hasExtra("user_data")){
                    intent1.setAction(EditProfileActivity.update_user);
                    startActivity(intent1);

                }else if(intent.hasExtra("doctor_data")){
                    intent1.setAction(EditProfileActivity.update_doctor);
                    startActivity(intent1);
                }
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getContext(), ChangePasswordActivity.class);
               if(intent.hasExtra("user_data")){
                   intent1.setAction(EditProfileActivity.update_user);
                   startActivity(intent1);

               }else if(intent.hasExtra("doctor_data")){
                   intent1.setAction(EditProfileActivity.update_doctor);
                   startActivity(intent1);
               }

            }
        });


        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(intent.hasExtra("user_data")){
                   Log.v(TAG,"update userrrrr");
                   Intent intent1=new Intent(getContext(), EditProfileActivity.class);
                   intent1.setAction(EditProfileActivity.update_user);
                   startActivity(intent1);

               }else  if(intent.hasExtra("doctor_data")){
                   Log.v(TAG,"update doctorrrrrrr");
                   Intent intent1=new Intent(getContext(), EditProfileActivity.class);
                   intent1.setAction("doctor");
                   startActivity(intent1);
               }

            }
        });

         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 logoutCalling();
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

    private void logoutCalling() {

        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<UserModel> call=retrofitInterface.logout();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(getContext(), response.body().getMessages().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }else {
                    Toast.makeText(getContext(), response.body().getMessages().toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

    }


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
