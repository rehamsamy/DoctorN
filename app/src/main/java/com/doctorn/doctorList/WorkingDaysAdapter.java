package com.doctorn.doctorList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.doctorn.R;
import java.util.List;

public class WorkingDaysAdapter  extends RecyclerView.Adapter<WorkingDaysAdapter.Holder> {
    Context mContext;
    List<String> worksDays;

    public WorkingDaysAdapter(Context mContext, List<String> worksDays) {
        this.mContext = mContext;
        this.worksDays = worksDays;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.work_days_list_item,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
       String x= worksDays.get(i);
      TextView dayName= holder.itemView.findViewById(R.id.day_txt);
      ImageView resrvImg=holder.itemView.findViewById(R.id.img_id);
      dayName.setText(x);
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