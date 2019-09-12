package com.doctorn.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.conversation.DoctorConversationActivity;
import com.doctorn.conversation.UserConservationActivity;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.userAccount.userAccount.articleFragment.ArticleDetailsActivity;
import com.doctorn.userAccount.userAccount.articleFragment.UserArticleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OurPageActivity extends AppCompatActivity implements OnItemClickInterface{

    @BindView(R.id.our_page_recycler_id)RecyclerView recyclerView;
    @BindView(R.id.our_page_id)TextView ourPageTab;
    @BindView(R.id.specialties_id)TextView specialitiesTab;
    @BindView(R.id.my_account_id)TextView myAccountTab;
    @BindView(R.id.conversation_id)TextView conservationTab;
    UserArticleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_page);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UserArticleAdapter(this,this);
        recyclerView.setAdapter(adapter);
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
        startActivity(new Intent(OurPageActivity.this, ArticleDetailsActivity.class));
    }
}
