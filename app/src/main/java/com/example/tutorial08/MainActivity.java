package com.example.tutorial08;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    TextView lblWelcome;
    String userName ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView lstUsername;
    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("Login",Context.MODE_PRIVATE);
        lblWelcome = findViewById(R.id.lblWelcome);
        userName = sharedPreferences.getString("Username","");
        editor = sharedPreferences.edit();

        data = myDB.getUsername();

        lstUsername = findViewById(R.id.lstUserdata);
        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                data
        );
        lstUsername.setAdapter(adapter);
        lstUsername.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,UserDetail.class);
                intent.putExtra("Username",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_manu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuLogout:
                mnu_onClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mnu_onClick(){
        new AlertDialog.Builder(this).setTitle("Logout")
                .setCancelable(false)
                .setMessage("Do you want to logout?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.remove("isLogin");
                        editor.remove("Username");
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Logout successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Stay Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
        //AlertDialog alert = builder.create();
        //alert.setTitle("Logout");
        //alert.show();


    }

}