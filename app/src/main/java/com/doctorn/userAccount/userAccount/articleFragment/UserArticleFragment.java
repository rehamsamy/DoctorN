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

import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;

public class UserArticleFragment extends Fragment implements OnItemClickInterface{

    RecyclerView recyclerView;
    UserArticleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.user_article_fragment,container,false);
        recyclerView=view.findViewById(R.id.articles_recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new UserArticleAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(),ArticleDetailsActivity.class));

    }
}
