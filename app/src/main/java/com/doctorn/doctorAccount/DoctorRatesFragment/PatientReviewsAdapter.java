package com.doctorn.doctorAccount.DoctorRatesFragment;

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
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ReviewsItem;

import java.util.List;

public class PatientReviewsAdapter extends RecyclerView.Adapter<PatientReviewsAdapter.Holder> {
    Context mContext;
    OnItemClickInterface onItemClickInterface;
    List<ReviewsItem> reviewsItemList;
    int flag;



    public PatientReviewsAdapter(Context mContext, OnItemClickInterface onItemClickInterface, List<ReviewsItem> reviewsItems,int flag) {
        this.mContext = mContext;
        this.onItemClickInterface=onItemClickInterface;
        this.reviewsItemList=reviewsItems;
        this.flag=flag;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_rates_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        ImageView favImg =holder.itemView.findViewById(R.id.fav_img_id);
        TextView specialistText=holder.itemView.findViewById(R.id.specialist_name_id);
        TextView doctorName=holder.itemView.findViewById(R.id.doctor_name_id);
        RatingBar ratingBar=holder.itemView.findViewById(R.id.rating_bar);
        ReviewsItem model=reviewsItemList.get(i);

            favImg.setVisibility(View.GONE);
            specialistText.setVisibility(View.GONE);

        doctorName.setText(model.getDoctorName());
        ratingBar.setRating(Float.valueOf(model.getUserRate()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onItemClickInterface.onItemClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reviewsItemList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
