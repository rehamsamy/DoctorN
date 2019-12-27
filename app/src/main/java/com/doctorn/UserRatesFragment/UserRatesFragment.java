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
import com.doctorn.models.FavoriteDataArrayModel;
import com.doctorn.models.FavoriteDoctorsModel;
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
    FavoriteDoctorsAdapter adapter;
    RecyclerView recyclerView;
    int current_page=1;
    RetrofitInterface retrofitInterface;
    TextView emptyListTxt;
    List<FavoriteDataArrayModel> modelList;
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
       modelList=new ArrayList<>();
           buildRecyclerReview();
            getReviews(current_page);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        x="fragment";
//            buildRecyclerReview();
//           getReviews(current_page);

    }

    private void getReviews(final int current_page) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("page",current_page);
        map.put("limit",10);
        map.put("api_token",LoginActivity.userModel.getToken());
        map.put("user_id",LoginActivity.user.getId());
        Call<FavoriteDoctorsModel> call=retrofitInterface.getMyFavoriteDoctors(LoginActivity.userModel.getToken(),current_page,
                10);
            progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<FavoriteDoctorsModel>() {
            @Override
            public void onResponse(Call<FavoriteDoctorsModel> call, Response<FavoriteDoctorsModel> response) {
                if (response.body().isStatus()) {
                    modelList.addAll(response.body().getFavorites().getFavoriteDataArrayModelList());
                    Log.v(TAG,"responce"+response.body().getFavorites().getFavoriteDataArrayModelList());
                    if(response.body().getFavorites().getFavoriteDataArrayModelList().size()>0){
                        Log.v("TAG","become 0");
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter.notifyItemRangeInserted(adapter.getItemCount(), modelList.size());
                        progressBar.setVisibility(View.GONE);
                    }
                }else if(modelList.size()==0&&current_page==1){
                    Log.v("TAG","become 1");
                    emptyListTxt.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<FavoriteDoctorsModel> call, Throwable t) {
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
        adapter=new FavoriteDoctorsAdapter(getContext(),UserRatesFragment.this, modelList,x);
        Log.v(TAG,"ssss"+ this.modelList.size());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        current_page++;
                        Log.v(TAG, "model size" + modelList.size());
                        getReviews(current_page);

            }

        });

    }


    @Override
    public void onItemClick(int position) {

    }
}
