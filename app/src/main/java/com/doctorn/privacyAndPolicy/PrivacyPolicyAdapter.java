package com.doctorn.privacyAndPolicy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.ConditionsModel;
import com.doctorn.models.PrivacyModel;
import com.doctorn.utils.PreferenceHelper;

public class PrivacyPolicyAdapter extends RecyclerView.Adapter<PrivacyPolicyAdapter.Holder> {
    Context mcontext;
   PrivacyModel model;

    public PrivacyPolicyAdapter(Context mcontext,PrivacyModel model) {

        this.mcontext = mcontext;
        this.model=model;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.privacy_policy_item_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        TextView label=holder.itemView.findViewById(R.id.privacy_policy_label_id);
        TextView body=holder.itemView.findViewById(R.id.privacy_policy_subject_id);
        if(PreferenceHelper.getValue(mcontext).equals("ar")) {
            body.setText(model.getDescAr());

        }else {
            body.setText(model.getDescEn());
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
