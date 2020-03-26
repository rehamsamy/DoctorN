package com.doctorn;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorAccount.Reservations.ReservationsAdapter;
import com.doctorn.doctorAccount.Withdraw.WithdrawAdapter;
import com.doctorn.models.DoWithdrawRequestModel;
import com.doctorn.models.WalletModel;
import com.doctorn.models.WithdrawModel;
import com.doctorn.models.WithdrawModelItemItem;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceActivity extends AppCompatActivity {

    @BindView(R.id.signUp_back_img)
    ImageView signUpBackImg;
    @BindView(R.id.your_balance_value_id)
    TextView yourBalanceValueId;
    @BindView(R.id.withdraw_id)
    TextView withdrawId;
    @BindView(R.id.recycler_id)
    RecyclerView recyclerView;
    @BindView(R.id.empty_list)
    TextView emptyData;
    @BindView(R.id.progress_id)
    ProgressBar progressId;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.withdraw_btn)
    Button withdrawBtn;
    WithdrawAdapter adapter;
    int current_page=1;
    NetworkAvailable networkAvailable;
    List<WithdrawModelItemItem> withdrawModels=new ArrayList<>();
    RetrofitInterface retrofitInterface;
    Dialog mDialog;
    DailogUtil dailogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);
        mDialog=new Dialog(this);
        dailogUtil=new DailogUtil();
        if(networkAvailable.isNetworkAvailable()){
            buildRecyclerForWithdraw();
            getWithdrawForDoctor(current_page);
            getMyWalletBalance();
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }
    }

    private void getMyWalletBalance() {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Call<WalletModel> call=retrofitInterface.getMyWalletBalance(DoctorAccountActivity.user.getToken(),
                PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(Call<WalletModel> call, Response<WalletModel> response) {
                if(response.body().isStatus()){
                    yourBalanceValueId.setText(String .valueOf(response.body().getMywallet().getBalance())+" "+getString(R.string.currency_type));
                }
            }

            @Override
            public void onFailure(Call<WalletModel> call, Throwable t) {

            }
        });
    }


    private void buildRecyclerForWithdraw() {
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        adapter=new WithdrawAdapter(withdrawModels,getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getWithdrawForDoctor(current_page);
            }
        });
    }

    private void getWithdrawForDoctor(final int current_page) {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressId.setVisibility(View.VISIBLE);
        Call<WithdrawModel> call=retrofitInterface.getWithdraw( DoctorAccountActivity.user.getToken(),
                current_page,10, PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<WithdrawModel>() {
            @Override
            public void onResponse(Call<WithdrawModel> call, Response<WithdrawModel> response) {
                if(response.body().isStatus()){
                    if(response.body().getRequests().getWithdrawModelItem().size()>0){
                        withdrawModels.addAll(response.body().getRequests().getWithdrawModelItem());
                        adapter.notifyDataSetChanged();
                        progressId.setVisibility(View.GONE);
                    }else if(response.body().getRequests().getWithdrawModelItem().size()==0&& current_page==1){
                        emptyData.setVisibility(View.VISIBLE);
                        progressId.setVisibility(View.GONE);
                    }
                }else {
                    progressId.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<WithdrawModel> call, Throwable t) {
                progressId.setVisibility(View.GONE);
            }
        });



    }

    @OnClick(R.id.signUp_back_img)
    public void onClick() {
    }

    @OnClick(R.id.withdraw_btn)
    void withdrawClick(){
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.withdraw_amount_popup_layout);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        final TextInputEditText amountInput=mDialog.findViewById(R.id.withdraw_amount_input);
        Button doWithdrawBtn=mDialog.findViewById(R.id.withdraw_btn);
        doWithdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FUtilsValidation.isEmpty(amountInput,getString(R.string.edit_text_empty))
                && Integer.parseInt(amountInput.getText().toString())>=100) {
                    if(networkAvailable.isNetworkAvailable()) {
                        doWithdrawOperation(amountInput.getText().toString());
                    }else {
                        Toast.makeText(BalanceActivity.this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
                    }
                }else if(Integer.parseInt(amountInput.getText().toString())<100){
                    Toast.makeText(BalanceActivity.this, getString(R.string.enter_value_great_100), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void doWithdrawOperation(String amount) {
        final ProgressDialog progressDialog= dailogUtil.showProgress(BalanceActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Call<DoWithdrawRequestModel> call=retrofitInterface.doWithdrawRequest("application/json",amount, DoctorAccountActivity.user.getToken(),
                PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<DoWithdrawRequestModel>() {
            @Override
            public void onResponse(Call<DoWithdrawRequestModel> call, Response<DoWithdrawRequestModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(BalanceActivity.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
                 progressDialog.dismiss();
                 mDialog.dismiss();
                 getWithdrawDonePopup();
                 //startActivity(new Intent(getApplicationContext(),BalanceActivity.class));
                }else {
                    Toast.makeText(BalanceActivity.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    mDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DoWithdrawRequestModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private void getWithdrawDonePopup() {
        mDialog.setContentView(R.layout.withdraw_done_popup_layout);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.show();
       startActivity(new Intent(getApplicationContext(),BalanceActivity.class));
    }


    @OnClick(R.id.signUp_back_img)
    void backClick(){
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mDialog.dismiss();
        finish();
    }
}
