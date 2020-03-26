package com.doctorn.doctorAccount.Reservations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ReservationItemModelItem;
import com.doctorn.models.ReviewsItem;

import java.util.List;

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.Holder> {
    Context mContext;
    OnItemClickInterface onItemClickInterface;
    List<ReservationItemModelItem> reviewsItemList;



    public ReservationsAdapter(Context mContext, List<ReservationItemModelItem> reviewsItems) {
        this.mContext = mContext;
        this.reviewsItemList=reviewsItems;

    }


    public void setOnItemClick( OnItemClickInterface onItemClickInterface){
        this.onItemClickInterface=onItemClickInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.reservation_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        ImageView favImg =holder.itemView.findViewById(R.id.fav_img_id);
        TextView specialistText=holder.itemView.findViewById(R.id.specialist_name_id);
        TextView doctorName=holder.itemView.findViewById(R.id.doctor_name_id);
        RatingBar ratingBar=holder.itemView.findViewById(R.id.rating_bar);
       ReservationItemModelItem model=reviewsItemList.get(i);
       specialistText.setText(model.getReservationDatetime());
        Log.v("TAG","vvvv"+model.getReservationDatetime());

        doctorName.setText(model.getName());
        ratingBar.setRating(Float.valueOf(model.getDoctorTotalRate()));


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
