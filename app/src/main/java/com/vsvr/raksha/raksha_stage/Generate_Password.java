package com.vsvr.raksha.raksha_stage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Generate_Password extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.vsvr.raksha.raksha_stage.EXTRA_TEXT";
    public static final String EXTRA_PASS = "com.vsvr.raksha.raksha_stage.EXTRA_PASS";

    private FirebaseFirestore firestoreDatabase = FirebaseFirestore.getInstance();
    private CollectionReference collectionRef = firestoreDatabase.collection("User_One");

    String finalPass;
    {
        finalPass = "";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_password);

        EditText gen_appName = (EditText) findViewById(R.id.gen_appName);
        String appName = gen_appName.getText().toString();

        Button gen_Go = (Button) findViewById(R.id.gen_Go);

        gen_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAckPage();
            }
        });
    }
    public void openAckPage(){
        EditText gen_appName = (EditText) findViewById(R.id.gen_appName);
        String appName = gen_appName.getText().toString();

        if((appName.matches("^[A-Za-z].*$"))
                && (Character.isAlphabetic(appName.charAt(appName.length()-1)))
                && (appName.matches("[A-Za-z\\s+]{2,16}"))){

            String randPasscode = "";

            do{
                randPasscode = codeGenerator();
            }
            while(!((randPasscode.matches("^[A-Za-z].*$"))
                    && (randPasscode.matches(".*[a-zA-Z].*"))
                    && (randPasscode.matches(".*[0-9].*"))
                    && (randPasscode.matches(".*[@,#,$,%,&,*].*"))
                    && (Character.isAlphabetic(randPasscode.charAt(randPasscode.length()-1)))
            ));
            Intent intent = new Intent(this, Ack_Page.class);
            intent.putExtra(EXTRA_TEXT, appName);
            intent.putExtra(EXTRA_PASS, finalPass);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please enter a valid App Name", Toast.LENGTH_SHORT).show();
        }
    }
    public String codeGenerator(){

        Random random = new Random();

        int min = 10;
        int max = 10;

        int ranNum = random.nextInt(max+ 1 - min) + min;

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$%&*";
        int setLength = characters.length();

        String passCode = "";
        int passcodeLength = ranNum;

        char[] text = new char[ranNum];

        for(int i = 0; i < passcodeLength; i++){
            text[i] = characters.charAt(random.nextInt(setLength));
        }

        for(int i = 0; i < text.length; i++){
            passCode = passCode + text[i];
        }
        finalPass = passCode;

        return(finalPass);
    }

}