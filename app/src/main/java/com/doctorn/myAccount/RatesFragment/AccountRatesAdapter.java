package com.doctorn.myAccount.RatesFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorn.R;

public class AccountRatesAdapter extends RecyclerView.Adapter<AccountRatesAdapter.Holder> {
    Context mContext;

    public AccountRatesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_rates_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
