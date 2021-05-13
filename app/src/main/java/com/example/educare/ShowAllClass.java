package com.example.educare;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowAllClass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_classes);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView11);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView13);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView14);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView15);
        ImageView imageView7 = (ImageView) findViewById(R.id.imageView12);
        ImageView imgDel = (ImageView) findViewById(R.id.imageView12);


    }

//    public void monDay(View view) {
//        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "1");
//        startActivity(i);
//    }





    public void monDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "2");
        startActivity(i);
    }
    public void tueDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "3");
        startActivity(i);
    }

    public void wedDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "4");
        startActivity(i);
    }

    public void thuDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "5");
        startActivity(i);
    }

    public void friDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "6");
        startActivity(i);
    }

    public void satDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "7");
        startActivity(i);
    }

    public void sunDay(View view) {
        Intent i = new Intent(this, MainActivity2.class).putExtra("id", "1");
        startActivity(i);

    }

}