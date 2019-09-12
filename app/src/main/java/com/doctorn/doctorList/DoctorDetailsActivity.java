package com.doctorn.doctorList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.ReviewsItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctorDetailsActivity extends AppCompatActivity {

    @BindView(R.id.fav_img)ImageView rateDocImg;
    @BindView(R.id.doctor_name_id) TextView doctorName;
    @BindView(R.id.rating_bar_id) RatingBar ratingBar;
    Dialog mDialog;
    ReviewsItem model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        if(intent.hasExtra("doctor_data")){
           model=intent.getParcelableExtra("doctor_data");
           doctorName.setText(model.getDoctorName());
           ratingBar.setRating(Float.valueOf(model.getUserRate()));

        }
    }

    @OnClick(R.id.fav_img)
    void rateDoctorClick(){
        mDialog=new Dialog(this);
        mDialog.setContentView(R.layout.send_rate_docor_pop_up);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
       Button send= mDialog.findViewById(R.id.send_btn_id);
        RatingBar bar=mDialog.findViewById(R.id.rating_bar_id);

      final float starsCount=  bar.getRating();


      if(starsCount>=1){
          rateDocImg.setImageResource(R.drawable.favorite);
          mDialog.dismiss();
      }
       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(starsCount>=1){
                   mDialog.dismiss();
                   rateDocImg.setImageResource(R.drawable.favorite);

               }
           }
       });
    }


    @OnClick(R.id.back_eng)
    void backClick(){
        startActivity(new Intent(getApplicationContext(),DoctorListActivity.class));
    }
}
