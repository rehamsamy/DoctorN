package com.doctorn.userAccount.userAccount.articleFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.FavoriteDataArrayModel;
import com.doctorn.models.FavoriteDoctorsModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserArticleFragment extends Fragment implements OnItemClickInterface {

    RecyclerView recyclerView;
    UserArticleAdapter adapter;
    List<FavoriteDataArrayModel> favoriteAricleList = new ArrayList<>();
    int current_page=1;
  RetrofitInterface retrofitInterface;
  NetworkAvailable networkAvailable;
  TextView emptyData;
  ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_article_fragment, container, false);
        recyclerView = view.findViewById(R.id.articles_recycler_id);
        emptyData=view.findViewById(R.id.empty_list);
        progressBar=view.findViewById(R.id.progress_id);
        networkAvailable=new NetworkAvailable(getActivity());

        if(networkAvailable.isNetworkAvailable()){
            buildRecyclerViewForArticle();
            getMyFavoriteList(current_page);
        }else {
            Toast.makeText(getActivity(), getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private void buildRecyclerViewForArticle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new UserArticleAdapter(getContext(), UserArticleFragment.this, favoriteAricleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getMyFavoriteList(current_page);
            }
        });
    }

    private void getMyFavoriteList(final int current_page) {
        progressBar.setVisibility(View.VISIBLE);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<FavoriteDoctorsModel> call=retrofitInterface.getMyFavoriteArticle(
                LoginActivity.userModel.getToken(),current_page,10);
        call.enqueue(new Callback<FavoriteDoctorsModel>() {
            @Override
            public void onResponse(Call<FavoriteDoctorsModel> call, Response<FavoriteDoctorsModel> response) {
                if(response.body().isStatus()){
                    if(response.body().getFavorites().getFavoriteDataArrayModelList().size()>0){
                       favoriteAricleList.addAll(response.body().getFavorites().getFavoriteDataArrayModelList());
                       adapter.notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
                    }else if(response.body().getFavorites().getFavoriteDataArrayModelList().size()==0
                    &&current_page==1){
                        emptyData.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FavoriteDoctorsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getContext(), ArticleDetailsActivity.class);
        intent.putExtra(ArticleDetailsActivity.ARTICLE_ID,favoriteAricleList.get(position).getFavoriteId());
        startActivity(intent);

    }
}
