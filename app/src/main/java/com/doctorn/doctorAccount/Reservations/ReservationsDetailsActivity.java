package com.doctorn.doctorAccount.Reservations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.ReservationItemModelItem;
import com.doctorn.utils.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.back_eng)
    ImageView backEng;
    @BindView(R.id.item_name_toolbar)
    TextView itemNameToolbar;
    @BindView(R.id.doctor_name_id)
    TextView doctorNameId;
    @BindView(R.id.rating_bar_id)
    RatingBar ratingBarId;
    @BindView(R.id.reservation_time_value)
    TextView reservationTimeValue;
    @BindView(R.id.payment_method_value)
    TextView paymentMethodValue;
    @BindView(R.id.transaction_id_value)
    TextView transactionIdValue;
    @BindView(R.id.paid_amount_value)
    TextView paidAmountValue;
    @BindView(R.id.consultation_duration_value)
    TextView consultationDurationValue;
    @BindView(R.id.consultation_price_value)
    TextView consultationPriceValue;
    @BindView(R.id.work_days_value)
    TextView workDaysValue;
    @BindView(R.id.cencel_reservation_btn)
    Button cencelReservationBtn;
    ReservationItemModelItem model;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_details);
        ButterKnife.bind(this);

        intent=getIntent();
        if(intent.hasExtra("model")){
            model=intent.getParcelableExtra("model");
            initiateViews(model);
        }

    }

    private void initiateViews(ReservationItemModelItem model) {
        itemNameToolbar.setText(model.getName());
        doctorNameId.setText(model.getName());
        ratingBarId.setRating(Float.valueOf(model.getDoctorTotalRate()));
        reservationTimeValue.setText(model.getReservationDatetime());
        consultationDurationValue.setText(String.valueOf(model.getConsultationDuration()));
        consultationPriceValue.setText(String.valueOf(model.getConsultationPrice()));
        paidAmountValue.setText(model.getPaidAmount());
        paymentMethodValue.setText(model.getPaymentMethod());
        transactionIdValue.setText(model.getTransactionId());
        workDaysValue.setText("");
        if(PreferenceHelper.getValue(getApplicationContext()).equals("ar")){
            for(String x:model.getWorkdaysAr()){
                workDaysValue.append(x+",");
            }
        }else if(PreferenceHelper.getValue(getApplicationContext()).equals("en")){
            for(String x:model.getWorkdaysEn()){
                workDaysValue.append(x);
            }
        }

    }

    @OnClick({R.id.back_eng, R.id.cencel_reservation_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_eng:
                break;
            case R.id.cencel_reservation_btn:
                break;
        }
    }
}
