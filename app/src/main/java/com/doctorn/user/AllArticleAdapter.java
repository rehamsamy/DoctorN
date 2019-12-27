package com.doctorn.user;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ArticleDataArrayModel;
import com.doctorn.models.FavoriteDataArrayModel;
import com.doctorn.utils.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllArticleAdapter extends RecyclerView.Adapter<AllArticleAdapter.Holder> {
    Context context;
    OnItemClickInterface onItemClickInterface;
    List<ArticleDataArrayModel>favoriteDataArrayModels;

    public AllArticleAdapter(Context context,OnItemClickInterface onItemClickInterface,List<ArticleDataArrayModel> models) {
        this.context = context;
        this.onItemClickInterface=onItemClickInterface;
        this.favoriteDataArrayModels=models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.article_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        ArticleDataArrayModel model=favoriteDataArrayModels.get(position);
        ImageView articleImg=holder.itemView.findViewById(R.id.article_img_id);
        TextView articleTxt=holder.itemView.findViewById(R.id.article_text_value_id);
        TextView likeCount=holder.itemView.findViewById(R.id.like_count_value);
        TextView commentCount=holder.itemView.findViewById(R.id.comment_count_id);

        Picasso.get().load(RetrofitClientInstance.base_url+"/"+
                model.getImage()).error(R.drawable.doctor)
                .into(articleImg);
        articleTxt.setText(model.getTitle());
        likeCount.setText(String.valueOf(model.getLikesNo()));
        commentCount.setText(String.valueOf(model.getCommentNo()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickInterface.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favoriteDataArrayModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
