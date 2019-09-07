package com.doctorn.myAccount.RatesFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorn.R;

public class AccountRatesFragment extends Fragment {
    AccountRatesAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account_rates_fragment,container,false);
      recyclerView=(RecyclerView) view.findViewById(R.id.rates_recycler_id);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter=new AccountRatesAdapter(getContext());
      recyclerView.setAdapter(adapter);
        return view;
    }
}
