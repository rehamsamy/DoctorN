package com.doctorn.myFinancail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doctorn.R;
import com.doctorn.models.TansactionListItem;
import com.doctorn.utils.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinancailDetailsActivity extends AppCompatActivity {

    @BindView(R.id.transaction_date_value)
    TextView transactionDateValue;
    @BindView(R.id.transaction_type_value)
    TextView transactionTypeValue;
    @BindView(R.id.transaction_method_value)
    TextView transactionMethodValue;
    @BindView(R.id.transaction_reason_value)
    TextView transactionReasonValue;
    @BindView(R.id.transaction_status_value)
    TextView transactionStatusValue;
    @BindView(R.id.bank_name_value)
    TextView bankNameValue;
    @BindView(R.id.bank_sender_name_value)
    TextView bankSenderNameValue;
    @BindView(R.id.bank_acc_number_value)
    TextView bankAccNumberValue;
    @BindView(R.id.bank_transfer_reference_number_value)
    TextView bankTransferReferenceNumberValue;
    @BindView(R.id.bank_receipt_link_value)
    ImageView bankReceiptLinkValue;
    @BindView(R.id.item_name_toolbar)
    TextView transactionAmount;
    Intent intent;
    TansactionListItem model;
    @BindView(R.id.bank_name_txt)
    TextView bankNameTxt;
    @BindView(R.id.bank_sender_name_txt)
    TextView bankSenderNameTxt;
    @BindView(R.id.bank_acc_number_txt)
    TextView bankAccNumberTxt;
    @BindView(R.id.bank_transfer_reference_number_txt)
    TextView bankTransferReferenceNumberTxt;
    @BindView(R.id.bank_receipt_link_txt)
    TextView bankReceiptLinkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financail_details);
        ButterKnife.bind(this);
        intent = getIntent();
        if (intent.hasExtra("model")) {
            model = intent.getParcelableExtra("model");

            iniatilizeUI(model);
        }
    }

    private void iniatilizeUI(TansactionListItem model) {
        transactionDateValue.setText(model.getTransactionDate());
        transactionMethodValue.setText(model.getTransactionMethod());
        transactionReasonValue.setText(model.getTransactionReason());
        transactionStatusValue.setText(model.getTransactionStatus());
        transactionTypeValue.setText(model.getTransactionType());
        transactionAmount.setText(model.getTransactionAmount());


        if(model.getBankName()==null&&model.getBankReceiptLink()==null&&model.getBankAccNumber()==null&&model
        .getBankTransferReferenceNumber()==null&&model.getBankSenderName()==null){
            bankAccNumberTxt.setVisibility(View.GONE);
            bankReceiptLinkTxt.setVisibility(View.GONE);
            bankSenderNameTxt.setVisibility(View.GONE);
            bankNameTxt.setVisibility(View.GONE);
            bankTransferReferenceNumberTxt.setVisibility(View.GONE);
            bankTransferReferenceNumberValue.setVisibility(View.GONE);
            bankNameValue.setVisibility(View.GONE);
            bankReceiptLinkValue.setVisibility(View.GONE);
            bankAccNumberValue.setVisibility(View.GONE);
            bankSenderNameValue.setVisibility(View.GONE);
        }else {
            bankNameValue.setText(model.getBankName());
            bankAccNumberValue.setText(model.getBankAccNumber());
            bankSenderNameValue.setText(model.getBankSenderName());
            Picasso.get().load(RetrofitClientInstance.base_url + "/" + model.getBankReceiptLink()).into(bankReceiptLinkValue);
            bankTransferReferenceNumberValue.setText(model.getBankTransferReferenceNumber());
            Log.v("TAG", "ccc " + RetrofitClientInstance.base_url + model.getBankReceiptLink());

        }


    }

    @OnClick(R.id.back_eng)
    public void onClick() {
    }
}
