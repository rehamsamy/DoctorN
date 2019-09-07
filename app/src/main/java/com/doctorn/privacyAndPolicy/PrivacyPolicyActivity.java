package com.doctorn.privacyAndPolicy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.models.ConditionsModel;
import com.doctorn.models.PrivacyModel;
import com.doctorn.models.UserModel;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private static final String TAG = PrivacyPolicyActivity.class.getSimpleName();
    PrivacyPolicyAdapter adapter;
    @BindView(R.id.policy_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.empty_list) TextView emptyList;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    RetrofitInterface retrofitInterface;
   public static PrivacyModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        ButterKnife.bind(this);

        getPrivacyAndPolicy();

    }

    private void getPrivacyAndPolicy() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<UserModel> call=retrofitInterface.getPrivacyAndPolicy();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body().isStatus()) {
                    model = response.body().getPrivacyModel();
                    if (model == null) {
                        emptyList.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new PrivacyPolicyAdapter(PrivacyPolicyActivity.this, model);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PrivacyPolicyActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "ccccc" + model.getDescEn());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


}
