package com.doctorn.myFinancail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.MyFinancailModel;
import com.doctorn.models.TansactionListItem;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.OnRecyclerInterface;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

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

public class AllFinancialActivity extends AppCompatActivity {

    @BindView(R.id.recycler_id)
    RecyclerView recyclerId;
    @BindView(R.id.empty_list)
    TextView emptyList;
    @BindView(R.id.progress_id)
    ProgressBar progressId;
    RetrofitInterface retrofitInterface;
    NetworkAvailable networkAvailable;
    @BindView(R.id.click_list)
    TextView clickList;
    private int current_page = 1;
    int flag;
    FinancailAdapter adapter;
    String token;
    List<TansactionListItem> tansactionListItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_finaniacl);
        ButterKnife.bind(this);
        networkAvailable = new NetworkAvailable(this);

        if (DoctorListActivity.user != null) {
            token = DoctorListActivity.user.getToken();
        } else if (DoctorAccountActivity.user != null) {
            token = DoctorAccountActivity.user.getToken();
        }
    }

    @OnClick({R.id.back_eng, R.id.all_financial_btn, R.id.all_bank_trans_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_eng:
                finish();
                break;
            case R.id.all_financial_btn:
                flag = 1;
                if (networkAvailable.isNetworkAvailable()) {
                    tansactionListItemList.clear();
                    buildRecyclerView();
                    getMyFinancail(1);
                    clickList.setVisibility(View.GONE);
                } else {
                    Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.all_bank_trans_btn:
                flag = 2;
                if (networkAvailable.isNetworkAvailable()) {
                    tansactionListItemList.clear();
                    buildRecyclerView();
                    getBankTransaction(1);
                    clickList.setVisibility(View.GONE);
                } else {
                    Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
                }

                break;
        }
    }


//    @OnClick(R.id.all_financial_btn)
//    void allFinancailClick() {
//        if (networkAvailable.isNetworkAvailable()) {
//            tansactionListItemList.clear();
//            buildRecyclerView();
//            getMyFinancail(1);
//        } else {
//            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @OnClick(R.id.all_bank_trans_btn)
//    void bankTransactionClick() {
//        if (networkAvailable.isNetworkAvailable()) {
//            tansactionListItemList.clear();
//            buildRecyclerView();
//            getBankTransaction(1);
//        } else {
//            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
//        }
//
//    }


    private void buildRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerId.setLayoutManager(manager);
        recyclerId.setHasFixedSize(true);
        adapter = new FinancailAdapter(tansactionListItemList, getApplicationContext());
        recyclerId.setAdapter(adapter);
        recyclerId.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                if (flag == 1) {
                    getMyFinancail(current_page);
                } else if (flag == 2) {
                    getBankTransaction(current_page);
                }
            }
        });

        adapter.setOnClick(new OnRecyclerInterface() {
            @Override
            public void onClick(int position) {
                TansactionListItem model = tansactionListItemList.get(position);
                Intent intent = new Intent(getApplicationContext(), FinancailDetailsActivity.class);
                intent.putExtra("model", model);
                Log.v("TAG", "xxx " + model.getBankReceiptLink());
                startActivity(intent);
            }
        });
    }

    private void getMyFinancail(final int current_page) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        progressId.setVisibility(View.VISIBLE);
        Call<MyFinancailModel> call = retrofitInterface.getMyFinancail(token, current_page, 5, PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<MyFinancailModel>() {
            @Override
            public void onResponse(Call<MyFinancailModel> call, Response<MyFinancailModel> response) {
                if (response.body().isStatus()) {
                    if (response.body().getTransactions().getTansactionList().size() > 0) {
                        tansactionListItemList.addAll(response.body().getTransactions().getTansactionList());
                        adapter.notifyDataSetChanged();
                        progressId.setVisibility(View.GONE);
                    } else if (response.body().getTransactions().getTansactionList().size() == 0 && current_page == 1) {
                        emptyList.setVisibility(View.VISIBLE);
                        progressId.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyFinancailModel> call, Throwable t) {
                progressId.setVisibility(View.GONE);
                Toast.makeText(AllFinancialActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("TAG", "onError" + t.getMessage());
            }
        });
    }


    private void getBankTransaction(final int current_page) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        progressId.setVisibility(View.VISIBLE);
        Map<String, Object> map = new HashMap<>();
        map.put("api_token", token);
        map.put("page", current_page);
        map.put("limit", 5);
        map.put("lang", PreferenceHelper.getValue(getApplicationContext()));

        Call<MyFinancailModel> call = retrofitInterface.getBankTransaction(map);
        call.enqueue(new Callback<MyFinancailModel>() {
            @Override
            public void onResponse(Call<MyFinancailModel> call, Response<MyFinancailModel> response) {
                if (response.body().isStatus()) {
                    if (response.body().getBankTransaction().getTansactionList().size() > 0) {
                        tansactionListItemList.addAll(response.body().getBankTransaction().getTansactionList());
                        adapter.notifyDataSetChanged();
                        progressId.setVisibility(View.GONE);
                        Log.v("TAG", "onError" + response.body().getBankTransaction().getTansactionList().size());
                    } else if (response.body().getBankTransaction().getTansactionList().size() == 0 && current_page == 1) {
                        emptyList.setVisibility(View.VISIBLE);
                        progressId.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyFinancailModel> call, Throwable t) {
                progressId.setVisibility(View.GONE);
                Toast.makeText(AllFinancialActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("TAG", "onError" + t.getMessage());
            }
        });
    }


    @OnClick(R.id.back_eng)
    void backClick(){
        finish();
    }
}
