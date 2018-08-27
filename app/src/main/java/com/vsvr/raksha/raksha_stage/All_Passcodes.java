package com.vsvr.raksha.raksha_stage;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.vsvr.raksha.raksha_stage.Ack_Page;

public class All_Passcodes extends AppCompatActivity {

    private static final String TAG = "ALL_PASSCODES_PAGE";

    String KEY_APPNAME = "appName";
    String KEY_PASSCODE = "appPasscode";

    String appName = "";
    String appPasscode = "";

    private FirebaseFirestore firestoreDatabase = FirebaseFirestore.getInstance();
    private CollectionReference collectionRef = firestoreDatabase.collection("User_One");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all__passcodes);

        TextView allPasswordArea = (TextView) findViewById(R.id.allPasswordArea);

        Button view_all = (Button) findViewById(R.id.view_all);

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAllPasscodes();
            }
        });
    }

    public void displayAllPasscodes() {
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            TextView allPasswordArea = (TextView) findViewById(R.id.allPasswordArea);

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                String data = "";

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Note note = documentSnapshot.toObject(Note.class);

                    String appName = note.getAppName();
                    String appPasscode = note.getAppPasscode();

                    data += "AppName: " + appName + "\nPasscode :" + appPasscode + "\n\n";
                }
                allPasswordArea.setText(data);
            }
        });
    }
}