package com.doctorn.UserRatesFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ReviewModel;
import com.doctorn.models.ReviewsItem;
import com.doctorn.models.User;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class UserRatesFragment extends Fragment implements OnItemClickInterface{
    UserRatesAdapter adapter;
    RecyclerView recyclerView;
    int current_page=1;
    RetrofitInterface retrofitInterface;
    TextView emptyListTxt;
    List<ReviewsItem> modelList;
    ProgressBar progressBar;
    int flag;
    User user;
    String x;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.user_rates_fragment,container,false);

        recyclerView=(RecyclerView) view.findViewById(R.id.rates_recycler_id);
        progressBar=view.findViewById(R.id.progress_id);
        emptyListTxt=view.findViewById(R.id.empty_list);
        // recyclerView.setAdapter(adapter);
       x="fragment";
            buildRecyclerReview();
            getReviews(current_page);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        x="fragment";
//            buildRecyclerReview();
//            getReviews(current_page);

    }

    private void getReviews(int current_page) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("page",current_page);
        map.put("limit",10);
        map.put("api_token",LoginActivity.userModel.getToken());
        map.put("user_id",LoginActivity.user.getId());
        Call<ReviewModel> call=retrofitInterface.getReviewByUserId(map);
            progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                if (response.body().isStatus()) {
                    progressBar.setVisibility(View.GONE);
                    modelList.addAll(response.body().getReviews());
                    Log.v(TAG,"responce"+response.body().getReviews());
                    adapter.notifyItemRangeInserted(adapter.getItemCount(), modelList.size());

                    if(response.body().getReviews().size()==0){
                        recyclerView.setVisibility(View.GONE);
                        emptyListTxt.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }else {
                    recyclerView.setVisibility(View.GONE);
                    emptyListTxt.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                emptyListTxt.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void buildRecyclerReview() {
        modelList=new ArrayList<>();
        final LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter=new UserRatesAdapter(getContext(),UserRatesFragment.this, modelList,x);
        Log.v(TAG,"ssss"+ this.modelList.size());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        current_page++;
                        if(modelList.size()==0){
                            current_page=1;
                            Log.v(TAG, "model size" + modelList.size());
                        }
                        Log.v(TAG, "model size" + modelList.size());
                        getReviews(current_page);



            }

        });

    }


    @Override
    public void onItemClick(int position) {

    }
}
