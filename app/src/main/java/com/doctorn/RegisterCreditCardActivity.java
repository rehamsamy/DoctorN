package com.doctorn;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegisterCreditCardActivity extends AppCompatActivity {

    @BindView(R.id.type_spinner_id)Spinner creditTypeSpinner;
    @BindView(R.id.month_spinner_id) Spinner monthSpinner;
    @BindView(R.id.year_spinner_id) Spinner yearSpinner;

    String [] creditType={"visa","credit card"};
    Integer []  mounthNum={1,2,3,4,5,6,7,8,9,10,11,12};
    Integer [] yearNum={2018,2019,2020,2021,2022,2023,2024,2025,2025};
    String creditValue;
   ArrayAdapter adapter1;
   ArrayAdapter<Integer> adapter2,adapter3;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_credit_card);
        ButterKnife.bind(this);
        adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,creditType);
        adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,mounthNum);
        adapter3=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,yearNum);
         creditTypeSpinner.setAdapter(adapter1);
         monthSpinner.setAdapter(adapter2);
         yearSpinner.setAdapter(adapter3);
    }


    @OnItemSelected(R.id.type_spinner_id)
    void onItemSelectedCredit(int index){
       creditValue=(String)adapter1.getItem(index);
    }

    @OnItemSelected(R.id.month_spinner_id)
    void setMonthSpinner(int index){

    }

    @OnItemSelected(R.id.year_spinner_id)
    void setYearSpinner(int index){

    }

    @OnClick(R.id.add_credit_card_btn)
    void addCreditCardClick(){

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.credit_confirmation_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ConstraintLayout layout=dialog.findViewById(R.id.con_root);
        layout.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button button= dialog.findViewById(R.id.continu_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FinalStepRegisterActivity.class));
            }
        });



    }
}
