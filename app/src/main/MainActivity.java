package com.example.bonimoo_imac_one.app.src.main;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String userName;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<User> users1 = new ArrayList<>();
    ListView listView;
    Button button;
    EditText editText;
    BaseAdapter baseAdapter;
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    SharedPreferences sharedPreferences ;
    Gson gson;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user_list", MODE_PRIVATE);
        Log.d("mainActivity", sharedPreferences.getString("userArray",""));
        listView = findViewById(R.id.listView);
         baseAdapter = new BaseAdapter(this, users);
        listView.setAdapter(baseAdapter);


        gson = new Gson();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBulder();
            }
        });


    }

    DialogInterface.OnClickListener dialogStatusClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            User user = new User();
             user.setName(editText.getText().toString());

             if(DialogInterface.BUTTON_POSITIVE == which) {
                user.setStatus(true);
                users.add(user);
                 users1.add(user);
                baseAdapter.refrash(users);
            }else {
                user.setStatus(false);
                 users1.add(user);
            }

            editor = sharedPreferences.edit();
            String userJasonString = gson.toJson(users1);
            editor.putString("userArray",userJasonString);
            editor.apply();
        }
    };

    private void createBulder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Enter User Name");
        editText = new EditText(this);
        editText.setLayoutParams(layoutParams);
        editText.setEms(50);
        builder.setView(editText);
        builder.setPositiveButton("Online", dialogStatusClickListener);
        builder.setNegativeButton("Offline", dialogStatusClickListener);

builder.show();

    }
}
