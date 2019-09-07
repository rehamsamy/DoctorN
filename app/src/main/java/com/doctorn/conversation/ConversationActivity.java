package com.doctorn.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.doctorn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity {

    @BindView(R.id.conversation_recycler_id)RecyclerView recyclerView;
    ConversationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ConversationAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
