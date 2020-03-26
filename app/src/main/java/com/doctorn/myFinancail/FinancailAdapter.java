package com.doctorn.myFinancail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.TansactionListItem;
import com.doctorn.models.WithdrawModelItemItem;
import com.doctorn.utils.OnRecyclerInterface;

import java.util.List;

public class FinancailAdapter extends RecyclerView.Adapter<FinancailAdapter.Holder> {
    List<TansactionListItem> tansactionListItems;
    OnRecyclerInterface mInterface;
    Context mContext;

    public FinancailAdapter(List<TansactionListItem> tansactionListItems, Context mContext) {
        this.tansactionListItems = tansactionListItems;
        this.mContext = mContext;
    }

    public void setOnClick(OnRecyclerInterface mInterface){
        this.mInterface=mInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.withdraw_list_item,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {

        TansactionListItem model= tansactionListItems.get(i);
        TextView operationNum=holder.itemView.findViewById(R.id.operation_num_value);
        TextView amountValue=holder.itemView.findViewById(R.id.money_value);
        TextView timeValue=holder.itemView.findViewById(R.id.time_value);
        TextView status=holder.itemView.findViewById(R.id.status);

        operationNum.setText(model.getTransactionMethod());
        amountValue.setText(String.valueOf(model.getTransactionAmount()));
        timeValue.setText(model.getTransactionDate());
        status.setText(model.getTransactionStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Log.v("TAG","sss xxx"+withdrawModels.size());
        return tansactionListItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
