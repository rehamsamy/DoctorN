package com.doctorn.doctorAccount.DoctorRatesFragment;

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

public class DoctorRatesFragment extends Fragment implements OnItemClickInterface{
    PatientReviewsAdapter adapter;
    RecyclerView recyclerView;
    int current_page=1;
    RetrofitInterface retrofitInterface;
    TextView emptyListTxt;
    List<ReviewsItem> modelList;
    ProgressBar progressBar;
    int flag;
  User user;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.doctor_rates_fragment,container,false);

      recyclerView=(RecyclerView) view.findViewById(R.id.rates_recycler_id);
      progressBar=view.findViewById(R.id.progress_id);
      emptyListTxt=view.findViewById(R.id.empty_list);
     // recyclerView.setAdapter(adapter);


            buildRecyclerReview();
            getReviews(current_page,LoginAsDoctorActivity.userModel.getToken(),LoginAsDoctorActivity.user.getId(),"doctor_id");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Intent intent=getActivity().getIntent();
//        if(intent.hasExtra("user_data")){
//            user=intent.getParcelableExtra("user_data");
//            flag=1;
//            Log.v(TAG,"fffff"+flag);
//        }else if(intent.hasExtra("doctor_data")){
//            user=intent.getParcelableExtra("doctor_data");
//            flag=2;
//            Log.v(TAG,"fffff"+flag);
//        }


//            buildRecyclerReview();
//            getReviews(current_page,LoginAsDoctorActivity.userModel.getToken(),LoginAsDoctorActivity.user.getId(),"doctor_id");


    }

    private void getReviews(int current_page,String token,int id,String type) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        final Map<String,Object> map=new HashMap<>();
        map.put("page",current_page);
        map.put("limit",10);
        map.put("api_token", token);
        map.put(type,id);
        progressBar.setVisibility(View.VISIBLE);
        Call<ReviewModel> call=retrofitInterface.getReviewByDoctorId(map);
        call.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                if (response.body().isStatus()) {
                    progressBar.setVisibility(View.GONE);
                    modelList.addAll(response.body().getReviews());
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
        adapter=new PatientReviewsAdapter(getContext(),DoctorRatesFragment.this, modelList,flag);
        Log.v(TAG,"ssss"+ this.modelList.size());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                current_page++;

                Log.v(TAG, "model size" + modelList.size());
                getReviews(current_page, LoginAsDoctorActivity.userModel.getToken(), LoginAsDoctorActivity.user.getId(), "doctor_id");

            }
        });

    }



    @Override
    public void onItemClick(int position) {

    }
}
