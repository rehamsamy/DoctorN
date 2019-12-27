package com.doctorn.userAccount.userAccount.articleFragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.models.ArticleDataArrayModel;
import com.doctorn.models.Articles;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailsActivity extends AppCompatActivity {
    public static final String ARTICLE_MODEL = "article_model";
    public static final String ARTICLE_ID = "article_id";
    @BindView(R.id.article_label_id)TextView articleTitleTxt;
    @BindView(R.id.article_subject_id)TextView articleBodyTxt;
    @BindView(R.id.article_img_id) ImageView articleImg;
    @BindView(R.id.comment_count_value)TextView commentValueTxt;
    @BindView(R.id.like_count_value)TextView likeValueTxt;
    @BindView(R.id.share_img)ImageView shaerImg;
    public static ArticleDataArrayModel model;
    int id;
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);
        Intent intent=getIntent();
       networkAvailable =new NetworkAvailable(this);

        if(intent.hasExtra(ARTICLE_MODEL)){
            model=intent.getParcelableExtra(ARTICLE_MODEL);
            articleBodyTxt.setText(model.getBody());
            articleTitleTxt.setText(model.getTitle());
            Picasso.get().load(RetrofitClientInstance.base_url+"/"
                    +model.getImage()).error(R.drawable.doctor).into(articleImg);

            commentValueTxt.setText(String.valueOf(model.getCommentNo()));
            likeValueTxt.setText(String.valueOf(model.getLikesNo()));

        }else if( intent.hasExtra(ARTICLE_ID)){

            id=intent.getIntExtra(ARTICLE_ID,1);
            Log.v("TAG","entered dd"+id);
            if(networkAvailable.isNetworkAvailable()){
               getArticleDetailsById(id);
            }else {
                Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
            }

        }



    }

    private void getArticleDetailsById(int id) {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        Call<Articles> call=retrofitInterface.getArticleDetails(LoginActivity.userModel.getToken(),id);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if(response.body().getStatus()){
                   model=response.body().getArticleDataArrayModel();
                    articleBodyTxt.setText(model.getBody());
                    articleTitleTxt.setText(model.getTitle());
                    Picasso.get().load(RetrofitClientInstance.base_url+"/"
                            +model.getImage()).error(R.drawable.doctor).into(articleImg);

                    commentValueTxt.setText(String.valueOf(model.getCommentNo()));
                    likeValueTxt.setText(String.valueOf(model.getLikesNo()));

                    Log.v("TAG","aaaaaaaa"+response.body().getArticleDataArrayModel().getTitle());
                    Toast.makeText(ArticleDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ArticleDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Toast.makeText(ArticleDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.share_img)
    void shareArticleDetails(){
//       Intent intent=new Intent(Intent.ACTION_SENDTO);
//       intent.setType("plan/txt")
    }

}
