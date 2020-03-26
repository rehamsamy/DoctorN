package com.doctorn.doctorAccount.Reservations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.conversation.DoctorConversationActivity;
import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.interfaces.OnItemClickInterface;
import com.doctorn.models.ReservationItemModelItem;
import com.doctorn.models.ReservationModel;
import com.doctorn.models.ReservationsModel;
import com.doctorn.utils.EndlessRecyclerViewScrollListener;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationsActivity extends AppCompatActivity {

    @BindView(R.id.recycler_id)
    RecyclerView recyclerView;
    @BindView(R.id.empty_list)
    TextView emptyData;
    @BindView(R.id.progress_id)
    ProgressBar progressBar;
    ReservationsAdapter adapter;
    NetworkAvailable networkAvailable;
    List<ReservationItemModelItem> reservationsModels=new ArrayList<>();
    int current_page=1;
    RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        ButterKnife.bind(this);
        networkAvailable=new NetworkAvailable(this);

        if(networkAvailable.isNetworkAvailable()){
            buildRecyclerForReservations();
           getMyReservations(current_page);
        }else {
            Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_LONG).show();
        }
    }

    private void getMyReservations(final int current_page) {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        Call<ReservationsModel> call=retrofitInterface.getReservations(DoctorAccountActivity.user.getToken(),current_page,10,
                PreferenceHelper.getValue(getApplicationContext()));
        call.enqueue(new Callback<ReservationsModel>() {
            @Override
            public void onResponse(Call<ReservationsModel> call, Response<ReservationsModel> response) {
                if(response.body().isStatus()){
                    if(response.body().getReservations().getReservationItemModel().size()>0){
                        reservationsModels.addAll(response.body().getReservations().getReservationItemModel());
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }else if(response.body().getReservations().getReservationItemModel().size()==0&&current_page==1){
                        emptyData.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReservationsModel> call, Throwable t) {
                Toast.makeText(ReservationsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void buildRecyclerForReservations() {
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        adapter=new ReservationsAdapter(getApplicationContext(),reservationsModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getMyReservations(current_page);
            }
        });


        adapter.setOnItemClick(new OnItemClickInterface() {
            @Override
            public void onItemClick(int position) {
               ReservationItemModelItem modelItem= reservationsModels.get(position);
                Intent intent=new Intent(getApplicationContext(),ReservationsDetailsActivity.class);
                intent.putExtra("model",modelItem);
                startActivity(intent);
            }
        });

    }


    @OnClick(R.id.conversation_id)
    void conversationClick(){
        Intent intent=new Intent(getApplicationContext(), DoctorConversationActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.reservations_id)
    void reservationClick(){
        Intent intent=new Intent(getApplicationContext(), ReservationsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.my_account_id)
    void accountClick(){
        startActivity(new Intent(getApplicationContext(),DoctorAccountActivity.class));
    }

}
