package com.doctorn.UserRatesFragment;

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
import com.doctorn.models.FavoriteDataArrayModel;
import com.doctorn.models.ReviewsItem;

import java.util.List;

import static android.content.ContentValues.TAG;

public class FavoriteDoctorsAdapter extends RecyclerView.Adapter<FavoriteDoctorsAdapter.Holder> {
    Context mContext;
    OnItemClickInterface onItemClickInterface;
    List<FavoriteDataArrayModel> reviewsItemList;

    String x;



    public FavoriteDoctorsAdapter(Context mContext,OnItemClickInterface onItemClickInterface,List<FavoriteDataArrayModel> reviewsItems,String x) {
        this.mContext = mContext;
        this.onItemClickInterface=onItemClickInterface;
        this.reviewsItemList=reviewsItems;
        this.x=x;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_rates_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        FavoriteDataArrayModel model=reviewsItemList.get(i);
        ImageView favImg =holder.itemView.findViewById(R.id.fav_img_id);
        TextView specialistText=holder.itemView.findViewById(R.id.specialist_name_id);
        RatingBar ratingBar=holder.itemView.findViewById(R.id.rating_bar);
        TextView name=holder.itemView.findViewById(R.id.doctor_name_id);

        if(x.equals("doctor")){
            favImg.setVisibility(View.GONE);
            Log.v(TAG,"user rates flag1");
        }else if(x.equals("fragment")){
            Log.v(TAG,"user rates flag2");
            favImg.setVisibility(View.VISIBLE);
        }

//        name.setText(model.ge);
//        ratingBar.setRating(Float.valueOf(model.getUserRate()));
//        specialistText.setVisibility(View.VISIBLE);
//        specialistText.setText(model.getUserName());

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
