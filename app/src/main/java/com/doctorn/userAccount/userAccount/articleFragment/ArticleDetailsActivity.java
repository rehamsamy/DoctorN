package com.doctorn.userAccount.userAccount.articleFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.R;
import com.doctorn.models.ArticleDataArrayModel;
import com.doctorn.models.Articles;
import com.doctorn.models.FavoriteDoctorsModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
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
    @BindView(R.id.share_img)ImageView shareImg;
    @BindView(R.id.like_img)ImageView likeImg;
    public static ArticleDataArrayModel model;
    DailogUtil dailogUtil;
    String title,body;
    int id;
    NetworkAvailable networkAvailable;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        dailogUtil=new DailogUtil();
       networkAvailable =new NetworkAvailable(this);

        if(intent.hasExtra(ARTICLE_MODEL)){
            model=intent.getParcelableExtra(ARTICLE_MODEL);
            articleBodyTxt.setText(model.getBody());
            articleTitleTxt.setText(model.getTitle());
            Picasso.get().load(RetrofitClientInstance.base_url+"/"
                    +model.getImage()).error(R.drawable.doctor).into(articleImg);

            commentValueTxt.setText(String.valueOf(model.getCommentNo()));
            likeValueTxt.setText(String.valueOf(model.getLikesNo()));
            title=model.getTitle();
            body=model.getBody();


        }else if( intent.hasExtra(ARTICLE_ID)){

            id=intent.getIntExtra(ARTICLE_ID,1);
            Log.v("TAG","entered dd"+id);
            likeImg.setImageResource(R.drawable.heart);
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

                    title=model.getTitle();
                    body=model.getBody();

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


    @OnClick(R.id.like_img)
    void likeArticleClick() {
        if (networkAvailable.isNetworkAvailable()) {
            addArticelToFav();
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
    }

    private void addArticelToFav() {
        retrofitInterface=RetrofitClientInstance.getRetrofit();
        final ProgressDialog progressDialog=dailogUtil.showProgress(ArticleDetailsActivity.this,getString(R.string.wait_loading),false);
        Call<FavoriteDoctorsModel> call=retrofitInterface.addArticleToFavorite(LoginActivity.userModel.getToken(),
                "article",id, PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<FavoriteDoctorsModel>() {
            @Override
            public void onResponse(Call<FavoriteDoctorsModel> call, Response<FavoriteDoctorsModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(ArticleDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    likeImg.setImageResource(R.drawable.heart);
                }else {
                    Toast.makeText(ArticleDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<FavoriteDoctorsModel> call, Throwable t) {
               progressDialog.dismiss();
            }
        });
    }


    @OnClick(R.id.share_img)
    void  setShareImg(){
        String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));


//        ShareCompat.IntentBuilder.from(getApplicationContext())
//                .setChooserTitle("")
//                .setText("")
//                .setType("text/plain")
//                .cr

    }

}
