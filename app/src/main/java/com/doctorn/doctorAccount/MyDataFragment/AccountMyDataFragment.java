package com.doctorn.doctorAccount.MyDataFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.Cons;
import com.doctorn.ContinuRegisterActivity;
import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.models.AllDoctorsModel;
import com.doctorn.models.DoctorInfoModel;
import com.doctorn.models.UserModel;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.doctorn.utils.Special;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountMyDataFragment extends Fragment {

    TextView specialistValue,studyValue,jobValue,languageValue;
    ImageView editDoctorData;
    String graduation_university,graduation_year,degree,special,language,profession;
    Intent intent;
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    Special specialVal;
    int doctor_id;
    String token;
    UserModel userModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_my_data_fragment, container, false);
        specialistValue = view.findViewById(R.id.specialist_value_id);
        studyValue = view.findViewById(R.id.study_value_id);
        jobValue = view.findViewById(R.id.job_value_id);
        languageValue = view.findViewById(R.id.language_value_id);
        networkAvailable = new NetworkAvailable(getContext());
        intent = getActivity().getIntent();

        if (networkAvailable.isNetworkAvailable()) {
            if (intent.hasExtra("doctor_data")) {
                userModel=intent.getParcelableExtra("doctor_data");
                token = userModel.getToken();
                doctor_id = userModel.getUser().getId();
                getDoctorData(token, doctor_id);
            }
        }
        else {
                Toast.makeText(getContext(), getContext().getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
            }

//        if(intent.getAction().equals(Cons.registerDoctorAction)){
//            graduation_university=intent.getStringExtra(Cons.graduation_universty);
//            graduation_year=intent.getStringExtra(Cons.graduation_year);
//            degree=intent.getStringExtra(Cons.degree);
//            profession=intent.getStringExtra(Cons.profession_license);
//            special=intent.getStringExtra(Cons.specialization);
//            language=intent.getStringExtra(Cons.languages);
//            specialistValue.setText(special);
//            studyValue.setText(graduation_university+"  -  "+graduation_year);
//            languageValue.setText(language);
//            jobValue.setText(special);
//            return view;
//        }else {

//            editDoctorData.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent1 = new Intent(getActivity(), ContinuRegisterActivity.class);
//                    intent1.setAction(Cons.registerDoctorAction);
//                    startActivity(intent1);
//                }
//            });


            return view;
            //}



    }

    private void getDoctorData(String token,int id) {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<AllDoctorsModel> call=retrofitInterface.getDoctorInfoDetails(token,doctor_id, PreferenceHelper.getValue(getContext()));
        call.enqueue(new Callback<AllDoctorsModel>() {
            @Override
            public void onResponse(Call<AllDoctorsModel> call, Response<AllDoctorsModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    DoctorInfoModel model=response.body().getDoctorInfoModel();
                    studyValue.setText(model.getGraduationUniversty()+"-"+model.getGraduationYear());
                    specialVal=new Special(getContext(),model.getSpecializationId());
                   Special.getSpecialist(model.getSpecializationId(),specialistValue);
                   if(PreferenceHelper.getValue(getContext()).equals("ar")){
                       for(String lan:response.body().getDoctorInfoModel().getLanguagesAr()) {
                           languageValue.setText(lan+" , ");
                       }
                       jobValue.setText(response.body().getDoctorInfoModel().getSpecializationArName());
                   }else if(PreferenceHelper.getValue(getContext()).equals("en")){
                       for(String lan:response.body().getDoctorInfoModel().getLanguagesEn()) {
                           languageValue.setText(lan+" , ");
                       }
                       jobValue.setText(response.body().getDoctorInfoModel().getSpecializationEnName());

                   }

                }else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllDoctorsModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
