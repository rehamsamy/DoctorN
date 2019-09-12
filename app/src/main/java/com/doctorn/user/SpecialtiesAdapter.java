package com.doctorn.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.UserspecialtiesItem;
import com.doctorn.utils.PreferenceHelper;

import java.util.List;

import butterknife.BindView;

public class SpecialtiesAdapter extends RecyclerView.Adapter<SpecialtiesAdapter.Holder> {
    Context mContext;
    List<UserspecialtiesItem> userspecialtiesItems;

    public SpecialtiesAdapter(Context mContext, List<UserspecialtiesItem> userspecialtiesItems) {
        this.mContext = mContext;
        this.userspecialtiesItems = userspecialtiesItems;
    }

    @NonNull
    @Override
    public SpecialtiesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.specialities_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtiesAdapter.Holder holder, int i) {

        ImageView arrowArImg=holder.itemView.findViewById(R.id.arrow_ar);
       ImageView arrowEngImg=holder.itemView.findViewById(R.id.arrow_eng);
        UserspecialtiesItem item=userspecialtiesItems.get(i);
      TextView specialitesText= holder.itemView.findViewById(R.id.specialist_id);
      if(PreferenceHelper.getValue(mContext).equals("ar")){
          arrowArImg.setVisibility(View.VISIBLE);
          arrowEngImg.setVisibility(View.GONE);
          specialitesText.setText(item.getSpecialtiesNameAr());
      }else if(PreferenceHelper.getValue(mContext).equals("en")){
          arrowArImg.setVisibility(View.GONE);
          arrowEngImg.setVisibility(View.VISIBLE);
          specialitesText.setText(item.getSpecialtiesNameEn());

      }


    }

    @Override
    public int getItemCount() {
        return userspecialtiesItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
