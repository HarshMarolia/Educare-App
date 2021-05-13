package com.example.educare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void todo(View view){
        Intent intent = new Intent(this, todolist.class);
        startActivity(intent);
    }

    public void musicx(View view){
        Intent intent = new Intent(this, musicx.class);
        startActivity(intent);
    }

    public void time_table(View view){
        Intent intent = new Intent(this, TimeTable.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}