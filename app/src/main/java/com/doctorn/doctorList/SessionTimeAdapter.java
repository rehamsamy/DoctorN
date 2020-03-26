package com.doctorn.doctorList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.doctorn.R;
import com.doctorn.models.Day1;
import com.doctorn.models.DaysArrayModelItem;
import com.doctorn.utils.OnRecyclerInterface;
import com.doctorn.utils.PreferenceHelper;

import java.util.List;

public class SessionTimeAdapter  extends RecyclerView.Adapter<SessionTimeAdapter.Holder> {
    Context mContext;
    List<String> sessionTimes;
    private int pos_selected=-1;
    OnRecyclerInterface mOnItemClick;

    public SessionTimeAdapter(Context mContext, List<String> sessionTimes) {
        this.mContext = mContext;
        this.sessionTimes = sessionTimes;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.work_days_list_item,viewGroup,false);
        return new Holder(view);
    }

    public void  setmOnItemClick(OnRecyclerInterface mOnItemClick){
        this.mOnItemClick=mOnItemClick;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        String session = sessionTimes.get(i);
        TextView dayName= holder.itemView.findViewById(R.id.day_txt);
        ImageView resrvImg=holder.itemView.findViewById(R.id.img_id);
        dayName.setText(session);
        Log.v("TAG","session is  "+session);


        if(pos_selected==i){
            resrvImg.setImageResource(R.drawable.reserved_dot_off);
        }else {
            resrvImg.setImageResource(R.drawable.reserved_dot);
        }


        resrvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos_selected=i;
                mOnItemClick.onClick(i);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return sessionTimes.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}