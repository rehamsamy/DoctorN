package com.doctorn.doctorAccount.Withdraw;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.WithdrawModelItemItem;

import java.util.List;

public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.Holder> {
    List<WithdrawModelItemItem> withdrawModels;
    Context mContext;

    public WithdrawAdapter(List<WithdrawModelItemItem> withdrawModels, Context mContext) {
        this.withdrawModels = withdrawModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.withdraw_list_item,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

       WithdrawModelItemItem model= withdrawModels.get(i);
        TextView operationNum=holder.itemView.findViewById(R.id.operation_num_value);
        TextView amountValue=holder.itemView.findViewById(R.id.money_value);
        TextView timeValue=holder.itemView.findViewById(R.id.time_value);
        TextView status=holder.itemView.findViewById(R.id.status);

        operationNum.setText(mContext.getString(R.string.operation_num)+model.getId());
        amountValue.setText(String.valueOf(model.getAmount()));
        timeValue.setText(model.getCreatedAt());
        status.setText(model.getStatus());

    }

    @Override
    public int getItemCount() {
        Log.v("TAG","sss xxx"+withdrawModels.size());
        return withdrawModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
