package com.doctorn.doctorList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.doctorn.models.AddReservationModel;
import com.doctorn.models.AllDoctorsModel;
import com.doctorn.models.AvailableSessionModel;
import com.doctorn.models.DaysArrayModelItem;
import com.doctorn.models.DoctorItemModel;
import com.doctorn.models.FavoriteDoctorsModel;
import com.doctorn.models.ReviewsItem;
import com.doctorn.models.WorkDaysModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.OnDateClickListener;
import com.doctorn.utils.OnRecyclerInterface;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.doctorn.voiceChat.FirebaseUser;
import com.doctorn.voiceChat.VoiceChatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    String data_time;
    private String transaction_id;
    String date;
    int position;
   // @BindView(R.id.fav_img)ImageView rateDocImg;
    @BindView(R.id.doctor_name_id) TextView doctorName;
    @BindView(R.id.rating_bar_id) RatingBar ratingBar;
    @BindView(R.id.specialist_id)TextView specialistTxt;
    @BindView(R.id.specialist_value_id)TextView specialistTxt2;
    @BindView(R.id.study_value_id)TextView studyTxt;
    @BindView(R.id.language_value_id)TextView languageTxt;
    @BindView(R.id.fav_img) ImageView favoriteImg;
    List<DaysArrayModelItem> daysModels=new ArrayList<>();
    List<String> sessionTimes=new ArrayList<>();
    WorkingDaysAdapter daysAdapter;
    SessionTimeAdapter sessionAdapter;

    RetrofitInterface retrofitInterface;
    NetworkAvailable networkAvailable;
    int doctor_id;

    Dialog mDialog;
    //ReviewsItem model;
    DoctorItemModel model;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);

       token= VoiceChatActivity.updateToken(FirebaseInstanceId.getInstance().getToken());


                Intent intent=getIntent();
        if(intent.hasExtra(DOCTOR_MODEl)){
           model=intent.getParcelableExtra(DOCTOR_MODEl);
            doctorName.setText(model.getName());
            doctor_id=intent.getIntExtra(DOCTOR_ID,1);
        }
        else if(intent.hasExtra("doc")){
            favoriteImg.setImageResource(R.drawable.favorite);
            model=intent.getParcelableExtra("doc");
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
                DoctorListActivity.user.getToken()
                , doctor_id,PreferenceHelper.getValue(getApplicationContext()));

        call.enqueue(new Callback<AllDoctorsModel>() {
            @Override
            public void onResponse(Call<AllDoctorsModel> call, Response<AllDoctorsModel> response) {
                if(response.body().isStatus()){
               studyTxt.setText(response.body().getDoctorInfoModel().getGraduationUniversty()+"-"+
                       response.body().getDoctorInfoModel().getGraduationYear());

                   if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
                       specialistTxt.setText(model.getSpecializationArName());
                       specialistTxt2.setText(model.getSpecializationArName());
                       for(String x:response.body().getDoctorInfoModel().getLanguagesAr()){
                           languageTxt.setText(x+" , ");
                       }
                   } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                       specialistTxt.setText(model.getSpecializationEnName());
                       specialistTxt2.setText(model.getSpecializationEnName());
                          for(String x:response.body().getDoctorInfoModel().getLanguagesEn()){
                               languageTxt.setText(x+" , ");
                          }

                   }
                }else {
                   // Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllDoctorsModel> call, Throwable t) {
               // Toast.makeText(DoctorDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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


    }

    private void addDoctorToFavoriteList(int doctor_id) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Map<String, Object> map = new HashMap<>();
        map.put("api_token",DoctorListActivity.user.getToken());
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
        RecyclerView timesRecycler=mDialog.findViewById(R.id.recycler_view_time);
        Button reservateBtn=mDialog.findViewById(R.id.reservate_btn_id);
        if(networkAvailable.isNetworkAvailable()) {
            getDoctorWorkingDays(daysRecycler,timesRecycler);
            //getAvailableSession(timesRecycler);
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }

        reservateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), PayTabActivity.class);
                in.putExtra(PaymentParams.MERCHANT_EMAIL, "eng.tahaalaa@gmail.com"); //this a demo account for testing the sdk
                in.putExtra(PaymentParams.SECRET_KEY,"k8r2dbtF0SCLcNBAHfBHiv7ppbL9tCVNLnQZyjEzbhko5WYMbAut6BTvNEFhhB53YV2xfH6rZqP4nORypl2vXV1LAG3Si8Vg4YOj");//Add your Secret Key Here
                in.putExtra(PaymentParams.LANGUAGE,PaymentParams.ENGLISH);
                in.putExtra(PaymentParams.TRANSACTION_TITLE, "Test Paytabs android library");
                in.putExtra(PaymentParams.AMOUNT, 200.000);

                in.putExtra(PaymentParams.CURRENCY_CODE, "USD");
                in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733");
                in.putExtra(PaymentParams.CUSTOMER_EMAIL, "customer-email@example.com");
                in.putExtra(PaymentParams.ORDER_ID, "123456");
                in.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2");

//Billing Address
                in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345");
                in.putExtra(PaymentParams.CITY_BILLING, "Manama");
                in.putExtra(PaymentParams.STATE_BILLING, "Manama");
                in.putExtra(PaymentParams.COUNTRY_BILLING, "BHR");
                in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Shipping Address
                in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
                in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
                in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
                in.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR");
                in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Payment Page Style
                in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc");

//Tokenization
                in.putExtra(PaymentParams.IS_TOKENIZATION, true);
                startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);

            }
        });

    }

    private void getDoctorWorkingDays(final RecyclerView daysRecycler, final RecyclerView timesRecycler) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Call<WorkDaysModel> call = retrofitInterface.getWorksDayByDoctorId();
        call.enqueue(new Callback<WorkDaysModel>() {
            @Override
            public void onResponse(Call<WorkDaysModel> call, Response<WorkDaysModel> response) {
                if (response.body().isStatus()) {
                    final WorkDaysModel model = response.body();
                    daysModels = model.getDaysArrayModel();
                    Log.v("TAG", "dayssss" + response.body().getDaysArrayModel().size());
                    daysRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    daysAdapter = new WorkingDaysAdapter(getApplicationContext(), daysModels);
                    daysRecycler.setAdapter(daysAdapter);
                    daysAdapter.setOnRecyclerClick(new OnDateClickListener() {
                        @Override
                        public void OnClick(int pos, ImageView imageView) {
                             position = pos;
                            date = daysModels.get(pos).getDayArName();
                            // imageView.setImageResource(R.drawable.reserved_dot_off);
                            Log.v("TAG","sellll"+daysModels.get(pos).getDayArName());
                            getAvailableSession(timesRecycler);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<WorkDaysModel> call, Throwable t) {

            }
        });

    }

    private void getAvailableSession(final RecyclerView timesRecycler) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();

        Call<AvailableSessionModel> call=retrofitInterface.getAvailableSession(DoctorListActivity.user.getToken(),11,PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<AvailableSessionModel>() {
            @Override
            public void onResponse(Call<AvailableSessionModel> call, final Response<AvailableSessionModel> response) {
                if(response.body().isStatus()){
                    sessionTimes=response.body().getNext7days().get(0).getDay1().getSessions24hours();
                    timesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    sessionAdapter=new SessionTimeAdapter(getApplicationContext(),sessionTimes);
                    timesRecycler.setAdapter(sessionAdapter);

                    sessionAdapter.setmOnItemClick(new OnRecyclerInterface() {
                        @Override
                        public void onClick(int position) {
                            Log.v("TAg","session is  "+ sessionTimes.get(position));
                            String time=sessionTimes.get(position).substring(0,5);
                            data_time=response.body().getNext7days().get(0).getDay1().getDate()+" "+time+":"+"00";
                            Log.v("TAg","day time is  "+ data_time);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<AvailableSessionModel> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tag", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            transaction_id=data.getStringExtra(PaymentParams.TRANSACTION_ID);
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
                //Log.e("Tag", data.getStringExtra("date"));

                if(networkAvailable.isNetworkAvailable()){
                    setReservationMethod(transaction_id);
                }else {
                    Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
                }

                //419882

            }
        }
    }

    private void setReservationMethod(String transaction_id) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("api_token",DoctorListActivity.user.getToken());
        map.put("doctor_id",doctor_id);
        map.put("reservation_datetime",data_time);
        map.put("payment_method","Paytabs");
        map.put("transaction_id",transaction_id);
        map.put("coupon",null);
        map.put("lang",PreferenceHelper.getValue(getApplicationContext()));

        Log.v("TAg","day time is  "+ data_time);

        Call<AddReservationModel> call=retrofitInterface.addReservation(map);
         call.enqueue(new Callback<AddReservationModel>() {
             @Override
             public void onResponse(Call<AddReservationModel> call, Response<AddReservationModel> response) {
                 if(response.body().isStatus()){
                     Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                     mDialog.dismiss();
                 }else {
                     Toast.makeText(DoctorDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<AddReservationModel> call, Throwable t) {

             }
         });


    }


    @OnClick(R.id.back_eng)
    void backClick(){
//        Intent intent=new Intent(getApplicationContext(), VoiceChatActivity.class);
//        intent.putExtra(DOCTOR_ID,doctor_id);
//        startActivity(intent);
        finish();
    }



    @OnClick(R.id.chat_btn_id)
    void  chatClick(){
        Log.v("TAG","xxxx");
        doctor_id=11;
        final Intent intent=new Intent(getApplicationContext(),VoiceChatActivity.class);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    FirebaseUser user=dataSnapshot1.getValue(FirebaseUser.class);
                    if(user.getKey().equals("doctor"+String.valueOf(doctor_id))){
                        intent.putExtra(DOCTOR_ID,user.getId());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
