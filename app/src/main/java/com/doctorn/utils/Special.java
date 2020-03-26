package com.doctorn.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.UserspecialtiesItem;
import com.doctorn.user.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class Special {

    static Context mContext;
    static  int specialValue;
    static String value;

    public Special(Context mContext,int specialValue) {
        this.mContext = mContext;
        this.specialValue=specialValue;
    }

    public static  void getSpecialist(final int specialValue, final TextView textView) {
        final Map<String, Object> map = new HashMap<>();
        final List<String> specialtiesModelList = new ArrayList<>();
        map.put("page", 1);
        map.put("limit", 10);
        map.put("api_token", DoctorAccountActivity.user.getToken());
        RetrofitInterface retrofitInterface = RetrofitClientInstance.getRetrofit();
        Call<SpecialtiesModel> call = retrofitInterface.getSpecialities(map);
        call.enqueue(new Callback<SpecialtiesModel>() {
            @Override
            public void onResponse(Call<SpecialtiesModel> call, Response<SpecialtiesModel> response) {
                if (response.body().isStatus()) {
                    for (int i = 0; i < response.body().getUserspecialties().size(); i++) {
                        UserspecialtiesItem item = response.body().getUserspecialties().get(i);
                        if (PreferenceHelper.getValue(mContext.getApplicationContext()).equals("ar")) {
                            specialtiesModelList.add(item.getSpecialtiesNameAr());
                            if(response.body().getUserspecialties().get(i).getId()==specialValue){
                                value= response.body().getUserspecialties().get(i).getSpecialtiesNameAr();
                                textView.setText(value);
                            }
                            Log.v(TAG, "value    " + specialtiesModelList.size());
//                            spinnerAdapter = new ArrayAdapter<String>(mContext.getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
//                            spinner.setAdapter(spinnerAdapter);
                        } else if (PreferenceHelper.getValue(mContext.getApplicationContext()).equals("en")) {
                            specialtiesModelList.add(item.getSpecialtiesNameEn());
                            if(response.body().getUserspecialties().get(i).getId()==specialValue){
                                value= response.body().getUserspecialties().get(i).getSpecialtiesNameEn();
                                textView.setText(value);
                            }
//                            spinnerAdapter = new ArrayAdapter<String>(mContext.getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
//                            spinner.setAdapter(spinnerAdapter);
                        }

                        Log.v(TAG, "special value     " + value);
                    }
                }

            }
            @Override
            public void onFailure(Call<SpecialtiesModel> call, Throwable t) {

            }
        });

        Log.v(TAG, "special valuexxxx     " + specialtiesModelList.size());


    }

}
