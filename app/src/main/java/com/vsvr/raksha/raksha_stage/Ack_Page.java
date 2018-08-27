package com.vsvr.raksha.raksha_stage;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Ack_Page extends AppCompatActivity {

    String TAG = "ACK_PAGE";

    String KEY_APPNAME = "appName";
    String KEY_PASSCODE = "appPasscode";

    FirebaseFirestore firestoreDatabase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ack_page);

        Intent intent = getIntent();
        String app_Name = intent.getStringExtra(Generate_Password.EXTRA_TEXT);
        String app_Passcode = intent.getStringExtra(Generate_Password.EXTRA_PASS);

        EditText ack_appName = findViewById(R.id.ack_appName);
        EditText ack_Passcode = findViewById(R.id.ack_Passcode);
        Button save_passcode = findViewById(R.id.ack_save);

        ack_appName.setText(app_Name);
        ack_Passcode.setText(app_Passcode);

        save_passcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetoDatabase();
            }
        });
    }
    public void savetoDatabase(){
        EditText ack_appName = findViewById(R.id.ack_appName);
        String appName = ack_appName.getText().toString();

        EditText ack_Passcode = findViewById(R.id.ack_Passcode);
        String appPasscode = ack_Passcode.getText().toString();

        Map<String, Object> dataNote = new HashMap<>();
        dataNote.put(KEY_APPNAME, appName);
        dataNote.put(KEY_PASSCODE, appPasscode);

        firestoreDatabase.collection("User_One").document(appName).set(dataNote).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Ack_Page.this, "Password Saved!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Ack_Page.this, "Password failed to save!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });
    }
}