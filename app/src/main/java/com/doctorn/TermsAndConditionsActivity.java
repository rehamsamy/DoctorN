package com.doctorn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doctorn.models.ConditionsModel;
import com.doctorn.models.PrivacyModel;
import com.doctorn.models.UserModel;
import com.doctorn.privacyAndPolicy.PrivacyPolicyAdapter;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionsActivity extends AppCompatActivity {

    PrivacyPolicyAdapter adapter;
    @BindView(R.id.conditions_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.notification_progress_id)
    ProgressBar progressBar;
    RetrofitInterface retrofitInterface;
   PrivacyModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        ButterKnife.bind(this);

        getConditions();

    }

    private void getConditions() {
        progressBar.setVisibility(View.VISIBLE);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<UserModel> call=retrofitInterface.getTermsAndConditions();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                model=response.body().getConditionsModel();
                if(response.body().isStatus()){
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(TermsAndConditionsActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter=new PrivacyPolicyAdapter(TermsAndConditionsActivity.this,model);
                    recyclerView.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    @OnClick(R.id.signUp_back_img)
    void backClick(){
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
