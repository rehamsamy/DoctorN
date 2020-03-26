package com.doctorn.voiceChat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.conversation.Message;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder> {
    Context mContext;
    List<Message> messageList;
    private int msg_type_left=0;
    private int msg_type_right=1;
    String currentUser;

    public ChatAdapter(Context mContext,  List<Message> messageList) {
        this.mContext = mContext;
        this.messageList=messageList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
          if(i==msg_type_right){
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
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(messageList.get(position).getSender().equals(currentUser)){
            Log.v("TAG","send dddd");
            return msg_type_right;

        }else{

            Log.v("TAG","receive vvvv");
           return msg_type_left;
        }
    }
}

