package com.doctorn.doctorList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.UserRatesFragment.UserRatesAdapter;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ReviewModel;
import com.doctorn.models.ReviewsItem;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.User;
import com.doctorn.models.UserspecialtiesItem;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
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
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorListActivity extends AppCompatActivity implements OnItemClickInterface {

    private static final String TAG = DoctorListActivity.class.getSimpleName();
    @BindView(R.id.doctor_recycler_id)
    RecyclerView recyclerView;
    @BindView(R.id.specialist_spinner_id)
    Spinner spinner;
    @BindView(R.id.specialist_value_id)
    TextView specialist;
    @BindView(R.id.progress_id)
    ProgressBar progressDialog;
    @BindView(R.id.empty_list)
    TextView emptyListTxt;
    //    @BindView(R.id.back_ar) ImageView backAr;
    @BindView(R.id.back_eng)
    ImageView backEng;
    RetrofitInterface retrofitInterface;
    List<String> specialtiesModelList = new ArrayList<>();
    List<ReviewsItem> reviewModels;
    UserRatesAdapter adapter;
    ArrayAdapter<String> spinnerAdapter;
    int current_page = 1;
    String specialValue;
    User user;
    public static String x;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        ButterKnife.bind(this);
        flag = 1;
        x = "doctor";
//
//        if(PreferenceHelper.getValue(this).equals("ar")){
//            backAr.setVisibility(View.VISIBLE);
//            backEng.setVisibility(View.GONE);
//        }else if(PreferenceHelper.getValue(this).equals("en")){
//            backAr.setVisibility(View.GONE);
//            backEng.setVisibility(View.VISIBLE);
//        }

        Log.v(TAG,"language  "+PreferenceHelper.getValue(DoctorListActivity.this));
        Intent intent = getIntent();
        if (intent.hasExtra("user_data")) {
            user = intent.getParcelableExtra("user_data");
            //flag=1;
        }


        getSpecialist();


        buidReviewsRecycler();
        getReviews(current_page);

    }


    @OnItemSelected(R.id.specialist_spinner_id)
    void onItemSelected(int index) {
        specialValue = (String) spinnerAdapter.getItem(index);
        specialist.setText(specialValue);
        Log.v(TAG, "spinner" + index);
    }

    @OnClick(R.id.back_eng)
    void backClick() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    private void getReviews(int current_page) {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Map<String, Object> map = new HashMap<>();
        map.put("page", current_page);
        map.put("limit", 10);
        map.put("api_token", LoginActivity.userModel.getToken());
        map.put("user_id", LoginActivity.user.getId());
        Call<ReviewModel> call = retrofitInterface.getReviewByUserId(map);

        progressDialog.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {

                if (response.body().isStatus()) {
                    progressDialog.setVisibility(View.GONE);
                    reviewModels.addAll(response.body().getReviews());
                    adapter.notifyItemRangeInserted(adapter.getItemCount(), reviewModels.size());
                    Log.v(TAG, "sizeee" + reviewModels.size());

                    if (reviewModels.size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        emptyListTxt.setVisibility(View.VISIBLE);
                        progressDialog.setVisibility(View.GONE);
                    }
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyListTxt.setVisibility(View.VISIBLE);
                    progressDialog.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                progressDialog.setVisibility(View.GONE);
            }
        });
    }

    private void buidReviewsRecycler() {
        reviewModels = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new UserRatesAdapter(getApplicationContext(), DoctorListActivity.this, reviewModels, x);
        Log.v(TAG, "ssss" + this.reviewModels.size());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getReviews(current_page);
            }
        });

    }

    @OnClick(R.id.account_img)
    void accountClick() {
        Intent intent = new Intent(DoctorListActivity.this, UserAccountActivity.class);
        intent.putExtra("user_data", user);
        startActivity(intent);
    }


    private void getSpecialist() {
        final Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("limit", 10);
        map.put("api_token", LoginActivity.userModel.getToken());
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        Call<SpecialtiesModel> call = retrofitInterface.getSpecialities(map);
        call.enqueue(new Callback<SpecialtiesModel>() {
            @Override
            public void onResponse(Call<SpecialtiesModel> call, Response<SpecialtiesModel> response) {
                if (response.body().isStatus()) {
                    for (int i = 0; i < response.body().getUserspecialties().size(); i++) {
                        UserspecialtiesItem item = response.body().getUserspecialties().get(i);
                        if (PreferenceHelper.getValue(getApplicationContext()).equals("ar")) {
                            specialtiesModelList.add(item.getSpecialtiesNameAr());
                            Log.v(TAG, "value    " + specialtiesModelList.size());
                            spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
                            spinner.setAdapter(spinnerAdapter);
                        } else if (PreferenceHelper.getValue(getApplicationContext()).equals("en")) {
                            specialtiesModelList.add(item.getSpecialtiesNameEn());
                            spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, specialtiesModelList);
                            spinner.setAdapter(spinnerAdapter);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<SpecialtiesModel> call, Throwable t) {

            }
        });


    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DoctorDetailsActivity.class);
        ReviewsItem model= reviewModels.get(position);
        intent.putExtra("doctor_data",model);
        startActivity(intent);
    }


}
