package com.doctorn.conversation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.voiceChat.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.Holder> {
    Context mContext;
    List<FirebaseUser> messagesList;
    OnItemIterface mItemIterface;
    String theLastMsg;
    Message message;

    interface OnItemIterface{
        void onClick(int pos);
    }

    public ConversationAdapter(Context mContext, List<FirebaseUser> messagesList,OnItemIterface mItemIterface) {
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
        FirebaseUser user=messagesList.get(i);
       TextView messTxt=holder.itemView.findViewById(R.id.conversation_subject_id);
        TextView date=holder.itemView.findViewById(R.id.conversation_time_id);
        TextView name=holder.itemView.findViewById(R.id.patient_name_id);

        messTxt.setText(user.getName());
        name.setText(user.getName());
         //date.setText(message.getDate());

         getLastMessage(user.getId(),messTxt,date ,name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItemIterface.onClick(i);
            }
        });

    }

    private void getLastMessage(final String id, final TextView messTxt,final TextView dateTxt,final TextView nameTxt) {
        theLastMsg="default";
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    message=dataSnapshot1.getValue(Message.class);
                    if(message.getReceiver().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())&& message.getSender().equals(id)
                            ||message.getReceiver().equals(id)&&message.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        theLastMsg=  message.getMessage();
                    }
                }

                messTxt.setText(theLastMsg);
                dateTxt.setText(message.getDate());
               // nameTxt.setText(message.);
            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

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
