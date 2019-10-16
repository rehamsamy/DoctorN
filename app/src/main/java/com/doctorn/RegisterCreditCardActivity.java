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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCreditCardActivity extends AppCompatActivity {

    @BindView(R.id.type_spinner_id)Spinner creditTypeSpinner;
    @BindView(R.id.month_spinner_id) Spinner monthSpinner;
    @BindView(R.id.year_spinner_id) Spinner yearSpinner;
    @BindView(R.id.credit_number_id) EditText creditNumberInput;
    @BindView(R.id.credit_name_id) EditText creditNameInput;
    @BindView(R.id.cvv_id)EditText cvvInput;
    RetrofitInterface retrofitInterface;
    String graduation_university,graduation_year,degree,special,profession,language;
    Intent intent;


    String [] creditType={"visa","credit card"};
    Integer []  mounthNum={1,2,3,4,5,6,7,8,9,10,11,12};
    Integer [] yearNum={2018,2019,2020,2021,2022,2023,2024,2025,2025};
    String creditValue;
   ArrayAdapter adapter1;
   ArrayAdapter<Integer> adapter2,adapter3;
    Dialog dialog;
    int mounthValue,yearValue;
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
         intent=getIntent();
         if(intent.getAction().equals(Cons.registerDoctorAction)){
             graduation_university=intent.getStringExtra(Cons.graduation_universty);
             graduation_year=intent.getStringExtra(Cons.graduation_year);
             degree=intent.getStringExtra(Cons.degree);
             profession=intent.getStringExtra(Cons.profession_license);
             special=intent.getStringExtra(Cons.specialization);
             language=intent.getStringExtra(Cons.languages);
         }
    }


    @OnItemSelected(R.id.type_spinner_id)
    void onItemSelectedCredit(int index){
       creditValue=(String)adapter1.getItem(index);
    }

    @OnItemSelected(R.id.month_spinner_id)
    void setMonthSpinner(int index){
        mounthValue=adapter2.getItem(index);

    }

    @OnItemSelected(R.id.year_spinner_id)
    void setYearSpinner(int index){
 yearValue=adapter3.getItem(index);
    }

    @OnClick(R.id.add_credit_card_btn)
    void addCreditCardClick(){

        addPaymentCard();

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
                Intent intent=new Intent(getApplicationContext(),FinalStepRegisterActivity.class);
                intent.putExtra(Cons.graduation_universty,graduation_university);
                intent.putExtra(Cons.graduation_year,graduation_year);
                intent.putExtra(Cons.specialization,special);
                intent.putExtra(Cons.degree,degree);
                intent.putExtra(Cons.profession_license,profession);
                intent.putExtra(Cons.languages,language);
                intent.setAction(Cons.registerDoctorAction);
                startActivity(intent);
            }
        });



    }

    private void addPaymentCard() {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Map<String, Object> map = new HashMap<>();
        map.put("card_type", creditValue);
        map.put("card_number", creditNumberInput.getText().toString());
        map.put("name_in_card", creditNameInput.getText().toString());
        map.put("exp_date", String.valueOf(yearValue + "-" + mounthValue));
        map.put("check_number", cvvInput.getText().toString());
        if (intent.getAction().equals(EditProfileActivity.update_user)) {
            map.put("api_token", LoginActivity.userModel.getToken());
        } else if (intent.getAction().equals(EditProfileActivity.update_doctor)) {
            map.put("api_token", LoginAsDoctorActivity.userModel.getToken());
        }

      Call<UserModel> call= retrofitInterface.addPaymentCard(map);
       call.enqueue(new Callback<UserModel>() {
           @Override
           public void onResponse(Call<UserModel> call, Response<UserModel> response) {
               if(response.body().isStatus()){
                   Toast.makeText(RegisterCreditCardActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(RegisterCreditCardActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<UserModel> call, Throwable t) {

           }
       });

    }
}
