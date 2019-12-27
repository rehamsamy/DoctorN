package com.doctorn.doctorList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.models.AllDoctorsModel;
import com.doctorn.models.DoctorItemModel;
import com.doctorn.models.FavoriteDoctorsModel;
import com.doctorn.models.ReviewsItem;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorDetailsActivity extends AppCompatActivity {

    public static final String DOCTOR_ID ="doctor_id";
    public static String DOCTOR_MODEl="doctor_model";
   // @BindView(R.id.fav_img)ImageView rateDocImg;
    @BindView(R.id.doctor_name_id) TextView doctorName;
    @BindView(R.id.rating_bar_id) RatingBar ratingBar;
    @BindView(R.id.specialist_id)TextView specialistTxt;
    @BindView(R.id.specialist_value_id)TextView specialistTxt2;
    @BindView(R.id.study_value_id)TextView studyTxt;
    @BindView(R.id.language_value_id)TextView languageTxt;
    @BindView(R.id.fav_img) ImageView favoriteImg;
    WorkingDaysAdapter daysAdapter;

    RetrofitInterface retrofitInterface;
    NetworkAvailable networkAvailable;
    int doctor_id;

    Dialog mDialog;
    //ReviewsItem model;
    DoctorItemModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);

       

                Intent intent=getIntent();
        if(intent.hasExtra(DOCTOR_MODEl)){
           model=intent.getParcelableExtra(DOCTOR_MODEl);
            doctorName.setText(model.getName());
            doctor_id=intent.getIntExtra(DOCTOR_ID,1);
        }

        if(networkAvailable.isNetworkAvailable()){
            getDoctorInfo();
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }
    }

    private void getDoctorInfo() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<AllDoctorsModel> call = retrofitInterface.getDoctorInfoDetails(
                LoginActivity.userModel.getToken()
                , doctor_id);

        call.enqueue(new Callback<AllDoctorsModel>() {
            @Override
            public void onResponse(Call<AllDoctorsModel> call, Response<AllDoctorsModel> response) {
                if(response.body().isStatus()){
               studyTxt.setText(response.body().getDoctorInfoModel().getGraduationUniversty()+"-"+
                       response.body().getDoctorInfoModel().getGraduationYear());
                   languageTxt.setText("Arabic , English");
                   if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
                       specialistTxt.setText(model.getSpecializationArName());
                       specialistTxt2.setText(model.getSpecializationArName());
                   } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                       specialistTxt.setText(model.getSpecializationEnName());
                       specialistTxt2.setText(model.getSpecializationEnName());
                   }
                }else {
                    Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllDoctorsModel> call, Throwable t) {
                Toast.makeText(DoctorDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.fav_img)
    void rateDoctorClick(){

        if(networkAvailable.isNetworkAvailable()){
            addDoctorToFavoriteList(doctor_id);
        }else {
            Toast.makeText(DoctorDetailsActivity.this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }

//        mDialog=new Dialog(this);
//        mDialog.setContentView(R.layout.send_rate_docor_pop_up);
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mDialog.show();
//       Button send= mDialog.findViewById(R.id.send_btn_id);
//        RatingBar bar=mDialog.findViewById(R.id.rating_bar_id);
//
//      final float starsCount=  bar.getRating();
//
//
//
//
//      if(starsCount>=1){
//          rateDocImg.setImageResource(R.drawable.favorite);
//          mDialog.dismiss();
//      }
//       send.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               if(starsCount>=1){
//                   mDialog.dismiss();
//                   rateDocImg.setImageResource(R.drawable.favorite);
//
//
//               }
//
//           }
//       });
    }

    private void addDoctorToFavoriteList(int doctor_id) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Map<String, Object> map = new HashMap<>();
        map.put("api_token",LoginActivity.userModel.getToken());
        map.put("favorite_type","doctor");
        map.put("doctor_id",doctor_id);

        Call<FavoriteDoctorsModel> call = retrofitInterface.addDoctorToFavorite(map);
        call.enqueue(new Callback<FavoriteDoctorsModel>() {
            @Override
            public void onResponse(Call<FavoriteDoctorsModel> call, Response<FavoriteDoctorsModel> response) {
                if (response.body().isStatus()) {
                    Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                  favoriteImg.setImageResource(R.drawable.favorite);
                } else {
                    Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
       }

       @Override
       public void onFailure(Call<FavoriteDoctorsModel> call, Throwable t) {

       }
   });

    }


    @OnClick(R.id.reservate_btn_id)
    void reservationClick(){
        mDialog=new Dialog(this);
        mDialog.setContentView(R.layout.reservate_doctor_pop_up);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        RecyclerView daysRecycler=mDialog.findViewById(R.id.recycler_view_days);
        if(networkAvailable.isNetworkAvailable()) {
            getDoctorWorkingDays(daysRecycler);
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }
       // daysAdapter=new D

    }

    private void getDoctorWorkingDays(RecyclerView daysRecycler) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();


    }


    @OnClick(R.id.back_eng)
    void backClick(){
        startActivity(new Intent(getApplicationContext(),DoctorListActivity.class));
    }
}
