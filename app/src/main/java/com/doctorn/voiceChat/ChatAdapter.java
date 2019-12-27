package com.doctorn.voiceChat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.conversation.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder> {
    Context mContext;
    List<Message> messageList;
    private int send_message=0;
    private int receive_message=1;

    public ChatAdapter(Context mContext,  List<Message> messageList) {
        this.mContext = mContext;
        this.messageList=messageList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
          if(i==send_message){
               view= LayoutInflater.from(mContext).inflate(R.layout.chat_right_item_row,viewGroup,false);
          }else {
              view= LayoutInflater.from(mContext).inflate(R.layout.chat_left_item_row,viewGroup,false);
          }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Message message=messageList.get(i);
       TextView messageTxt= holder.itemView.findViewById(R.id.message_txt);
       messageTxt.setText(message.getMessage());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).getToken().equals(VoiceChatActivity.token)){
            return send_message;
        }else {
           return receive_message;
        }
    }
}

