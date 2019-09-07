package com.doctorn.conversation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorn.R;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.Holder> {
    Context mContext;

    public ConversationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.conversation_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
