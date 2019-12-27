package com.doctorn.conversation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.Holder> {
    Context mContext;
    List<Message> messagesList;
    OnItemIterface mItemIterface;

    interface OnItemIterface{
        void onClick(int pos);
    }

    public ConversationAdapter(Context mContext, List<Message> messagesList,OnItemIterface mItemIterface) {
        this.mContext = mContext;
        this.messagesList=messagesList;
        this.mItemIterface=mItemIterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.conversation_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        Message message=messagesList.get(i);
        TextView messTxt=holder.itemView.findViewById(R.id.conversation_subject_id);
        TextView date=holder.itemView.findViewById(R.id.conversation_time_id);
        messTxt.setText(message.getMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemIterface.onClick(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
