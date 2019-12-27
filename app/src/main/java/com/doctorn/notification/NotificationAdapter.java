package com.doctorn.notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.NotificationDataArrayModel;
import com.doctorn.utils.PreferenceHelper;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {
    Context mContext;
    List<NotificationDataArrayModel> notificationModelList;

    public NotificationAdapter(Context mContext,List<NotificationDataArrayModel> models) {
        this.mContext = mContext;
        this.notificationModelList=models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.notification_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        NotificationDataArrayModel model=notificationModelList.get(i);
        TextView notificationLabel =holder.itemView.findViewById(R.id.notification_label_id);
        TextView notificationDescription=holder.itemView.findViewById(R.id.notification_msg_id);

        Log.v("TAG","aaaaaa"+notificationModelList.size()+model.getDescriptionAr());
        if(PreferenceHelper.getValue(mContext).equals("ar")){
            notificationLabel.setText(model.getTitleAr());
            notificationDescription.setText(model.getDescriptionAr());

        }else if(PreferenceHelper.getValue(mContext).equals("en")){
            notificationLabel.setText(model.getTitleEn());
            notificationDescription.setText(model.getDescriptionEn());

        }

//        notificationLabel.setText(model.getTitleEn());
//        notificationDescription.setText(model.getDescriptionEn());

    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
