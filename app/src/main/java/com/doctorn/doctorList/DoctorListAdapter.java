package com.doctorn.doctorList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.DoctorItemModel;
import com.doctorn.models.DoctorModel;
import com.doctorn.models.ReviewsItem;
import com.doctorn.utils.OnRecyclerInterface;
import com.doctorn.utils.PreferenceHelper;

import java.util.List;

public class DoctorListAdapter  extends RecyclerView.Adapter<DoctorListAdapter.Holder>{
    Context context;
    List<DoctorItemModel> doctorModelList;
    OnRecyclerInterface mInterface;

    public DoctorListAdapter(Context context, List<DoctorItemModel> doctorModelList) {
        this.context = context;
        this.doctorModelList = doctorModelList;
    }

    public void setRecyclerInterface(OnRecyclerInterface mInterface){
        this.mInterface=mInterface;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_rates_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        DoctorItemModel model=doctorModelList.get(i);
        ImageView favImg =holder.itemView.findViewById(R.id.fav_img_id);
        TextView specialistText=holder.itemView.findViewById(R.id.specialist_name_id);
        RatingBar ratingBar=holder.itemView.findViewById(R.id.rating_bar);
        TextView name=holder.itemView.findViewById(R.id.doctor_name_id);

        name.setText(model.getName());
        if(PreferenceHelper.getValue(context).equals("ar")){
            specialistText.setVisibility(View.VISIBLE);
            specialistText.setText(model.getSpecializationArName());

        }else if(PreferenceHelper.getValue(context).equals("en")){
            specialistText.setVisibility(View.VISIBLE);
            specialistText.setText(model.getSpecializationEnName());
        }
        name.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterface.onClick(i);
            }
        });

        //ratingBar.setRating(Float.valueOf(model.getUserRate()));


    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
