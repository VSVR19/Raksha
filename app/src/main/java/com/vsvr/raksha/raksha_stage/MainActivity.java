package com.vsvr.raksha.raksha_stage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ACK_PAGE";

    private static final String KEY_APPNAME = "appName";
    private static final String KEY_PASSCODE = "appPasscode";

    public static final String EXTRA_NAME = "com.vsvr.raksha.raksha_stage.EXTRA_NAME";
    public static final String EXTRA_PASS = "com.vsvr.raksha.raksha_stage.EXTRA_PASS";

    FirebaseFirestore firestoreDatabase = FirebaseFirestore.getInstance();
    private DocumentReference docRef = firestoreDatabase.document("User_One/gvxmRs1Cv2CDZyfCPJZ0");

    String appName;
    {
        appName = "";
    }
    String appPasscode;
    {
        appPasscode = "";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gen_password = (Button) findViewById(R.id.gen_password);
//        Button retrive_password = (Button) findViewById(R.id.retrive_password);
        Button home_all = (Button) findViewById(R.id.home_all);

        gen_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGenPasswordPage();
            }
        });

//        retrive_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDisplayPasswordPage();
//            }
//        });

        home_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllPasswordsPage();
            }
        });
    }
    public void openGenPasswordPage(){
        Intent intent = new Intent(this, Generate_Password.class);
        startActivity(intent);
    }
    public void openDipsPasswordPage(){
        Intent intent = new Intent(this, Display_Password.class);
        startActivity(intent);
    }
    public void openAllPasswordsPage(){
        Intent intent = new Intent(this, All_Passcodes.class);
        startActivity(intent);
    }
}