package com.doctorn.userAccount.userAccount.articleFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;

public class UserArticleAdapter extends RecyclerView.Adapter<UserArticleAdapter.Holder> {
    Context context;
    OnItemClickInterface onItemClickInterface;

    public UserArticleAdapter(Context context,OnItemClickInterface onItemClickInterface) {
        this.context = context;
        this.onItemClickInterface=onItemClickInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.article_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onItemClickInterface.onItemClick(position);
          }
      });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
