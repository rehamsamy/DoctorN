package com.doctorn.doctorList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.doctorn.R;
import com.doctorn.models.DaysArrayModelItem;
import com.doctorn.utils.OnDateClickListener;
import com.doctorn.utils.OnRecyclerInterface;
import com.doctorn.utils.PreferenceHelper;

import java.util.List;

public class WorkingDaysAdapter  extends RecyclerView.Adapter<WorkingDaysAdapter.Holder> {
    Context mContext;
    List<DaysArrayModelItem> worksDays;
    OnDateClickListener mOnClick;
    private int index_selected=-1;

    public WorkingDaysAdapter(Context mContext, List<DaysArrayModelItem> worksDays) {
        this.mContext = mContext;
        this.worksDays = worksDays;
    }

    public void setOnRecyclerClick( OnDateClickListener mOnClick){
        this.mOnClick=mOnClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.work_days_list_item,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        DaysArrayModelItem model= worksDays.get(i);
        TextView dayName= holder.itemView.findViewById(R.id.day_txt);
        final ImageView resrvImg=holder.itemView.findViewById(R.id.img_id);




        // resrvImg.setBackgroundResource(if(selectedPos == position) yourImageHightlight else yourNormalImage)

//        holder.accountImage.setOnClickListener {
//            selectedPos = position
//            notifyDataSetChanged()// or something like notifyItemChanged()...
//        }
//
        resrvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index_selected=i;
                mOnClick.OnClick(i,resrvImg);
                notifyDataSetChanged();
            }
        });

        if(index_selected==i){
            resrvImg.setImageResource(R.drawable.reserved_dot_off);
            Log.v("TAG","not selectedddd");
        }else {
            resrvImg.setImageResource(R.drawable.reserved_dot);
            Log.v("TAG","selectedddd");
        }

        if(PreferenceHelper.getValue(mContext).equals("ar")){
            dayName.setText(model.getDayArName());
        }else if(PreferenceHelper.getValue(mContext).equals("en")){
            dayName.setText(model.getDayEnName());
        }





    }

    @Override
    public int getItemCount() {
        return worksDays.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}