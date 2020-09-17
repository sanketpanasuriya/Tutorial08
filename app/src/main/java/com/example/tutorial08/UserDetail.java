package com.example.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;


public class UserDetail extends AppCompatActivity {
    DatabaseHelper myDB;
    String Username;
    TextView txtDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        txtDetail = findViewById(R.id.txtDetail);
        String UserAllData = "";
        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");
        myDB = new DatabaseHelper(this);
        Cursor cursor = myDB.getSingleuser(Username);
        cursor.moveToFirst();

        UserAllData+="Firstname : "+cursor.getString(1);
        UserAllData+="\nLastname : "+cursor.getString(2);
        UserAllData+="\nUsername : "+cursor.getString(3);
        UserAllData+="\nPassword : "+cursor.getString(4);
        UserAllData+="\nBranch : "+cursor.getString(5);
        UserAllData+="\nGender : "+cursor.getString(6);
        UserAllData+="\nCity : " +cursor.getString(7);
        UserAllData+="\nStatus : "+cursor.getString(8);

        txtDetail.setText(UserAllData);

    }
}