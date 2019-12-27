package com.doctorn.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.conversation.DoctorConversationActivity;
import com.doctorn.conversation.UserConservationActivity;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.AllArticleModel;
import com.doctorn.models.ArticleDataArrayModel;
import com.doctorn.notification.NotificationsActivity;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.userAccount.userAccount.articleFragment.ArticleDetailsActivity;
import com.doctorn.userAccount.userAccount.articleFragment.UserArticleAdapter;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurPageActivity extends AppCompatActivity implements OnItemClickInterface{

    @BindView(R.id.our_page_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.our_page_id)TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialitiesTab;
    @BindView(R.id.my_account_id)TextView myAccountTab;
    @BindView(R.id.conversation_id)TextView conservationTab;
    @BindView(R.id.empty_list) TextView emptyData;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    @BindView(R.id.notificaion_img) ImageView NotificationImg;
    int current_page=1;
    AllArticleAdapter adapter;
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    List<ArticleDataArrayModel> articlesModels=new ArrayList<>();
    ArticleDataArrayModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_page);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);
        if(networkAvailable.isNetworkAvailable()){
            buildRecyclerViewForAllArticles();
            getAllArticles(1);
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }


    }

    private void getAllArticles(final int current_page) {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        Call<AllArticleModel> call=retrofitInterface.getAllArticle(LoginActivity.userModel.getToken(),current_page,10);
        call.enqueue(new Callback<AllArticleModel>() {
            @Override
            public void onResponse(Call<AllArticleModel> call, Response<AllArticleModel> response) {
                if(response.body().isStatus()){
                    if(response.body().getArticles().getArticleDataArrayModelList().size()>0){
                        articlesModels.addAll(response.body().getArticles().getArticleDataArrayModelList());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(OurPageActivity.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                    }else if(response.body().getArticles().getArticleDataArrayModelList().size()==0&&current_page==1){
                        emptyData.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }
                    Toast.makeText(OurPageActivity.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(OurPageActivity.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AllArticleModel> call, Throwable t) {
                Toast.makeText(OurPageActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });




    }

    private void buildRecyclerViewForAllArticles() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new AllArticleAdapter(this,OurPageActivity.this,articlesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getAllArticles(current_page);
            }
        });
    }

    @OnClick(R.id.conversation_id)
    void conversationTab(){
        Intent intent=new Intent(OurPageActivity.this, UserConservationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.specialties_id)
    void specialitiesClick(){
        Intent intent=new Intent(OurPageActivity.this, SpecialtiesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_account_id)
    void myAccountClick(){
        Intent intent=new Intent(OurPageActivity.this, UserAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        model=articlesModels.get(position);
        Intent intent=new Intent(OurPageActivity.this, ArticleDetailsActivity.class);
       intent.putExtra(ArticleDetailsActivity.ARTICLE_MODEL,model);
        startActivity(intent);
    }

    @OnClick(R.id.notificaion_img)
    void setNotificationImg(){
      startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));

    }
}
