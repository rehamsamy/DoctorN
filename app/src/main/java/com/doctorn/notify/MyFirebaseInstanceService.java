package com.doctorn.notify;

import android.util.Log;

import com.doctorn.notify.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceService extends FirebaseInstanceIdService {

    public static final String token="MyFirebaseInstanceServi";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String refleshToken= FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        if(user !=null){
            //sendRegistrationToServer(refleshToken);
            sendRegistrationToServer(refleshToken);

        }

        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }



    private void sendRegistrationToServer(String refleshToken) {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Tokens");
        Token token=new Token(refleshToken);
        reference.child(user.getUid()).setValue(token);
        Log.v("TAG","tttttttt   " +refleshToken.toString());
    }
}
