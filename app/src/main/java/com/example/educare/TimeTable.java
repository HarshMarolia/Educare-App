package com.example.educare;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class TimeTable extends AppCompatActivity {
    DbHelper dbHelper;

    ListView lv;
    ImageView noList;
    String subjectsName[];
    String facultyName[];
    int images[];
    String times[];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper  = new DbHelper(this);
        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("Day of Week : ",String.valueOf(dayOfWeek));
        String day = "none";
        if(dayOfWeek == 2)
            day = "mon";
        else  if(dayOfWeek == 3)
            day="tue";
        else  if(dayOfWeek == 4)
            day="wed";
        else  if(dayOfWeek == 5)
            day="thur";
        else  if(dayOfWeek == 6)
            day="fri";
        else  if(dayOfWeek == 7)
            day="sat";
        else  if(dayOfWeek == 1)
            day="sun";


        setTitle("Time Table");
        int cnt = dbHelper.getCount(day);
        subjectsName = new String[cnt];
        facultyName = new String[cnt];
        images = new int[cnt];
        times = new String[cnt];



        mapItems(day);
        setContentView(R.layout.activity_time_table);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _int = new Intent(TimeTable.this, ClassForm.class);
                startActivity(_int);
            }
        });

        lv = findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(this, subjectsName, facultyName, times,images);
        lv.setAdapter(adapter);


// Code to hide title bar
//        getSupportActionBar().hide();

    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void mapItems(String day){

        ArrayList<MyClasses> allClass = dbHelper.fetchAllClass(day);
        int imgIndex = 1;
        try{


            for(int i =0;i<allClass.size();i++){
                Log.i("Subject:From Mapper",allClass.get(i).subjectName);
                Log.i("Faculty:From Mapper",allClass.get(i).facultyName);
                Log.i("Day:From Mapper",allClass.get(i).day);
                Log.i("Time:From Mapper",allClass.get(i).time);


                String daydb = allClass.get(i).day;
                Log.i("today day : ",day);
                Log.i("today day DATABSE: ",daydb);



                Log.i("same day : ","same");

                if(imgIndex >= 5)
                    imgIndex = 1;

                if(imgIndex == 1)
                    images[i] = R.drawable.img1;
                else if(imgIndex == 2)
                    images[i] = R.drawable.img2;
                else if(imgIndex == 3)
                    images[i] = R.drawable.img3;
                else
                    images[i] = R.drawable.img4;

                imgIndex +=1;

                subjectsName[i] = allClass.get(i).subjectName;
                facultyName[i] = allClass.get(i).facultyName;
                times[i]= allClass.get(i).time;


            }
        }catch (Exception e){
            Log.i("Exception:",e.toString());
        }
    }


    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        String subjects[];
        String facultyName[];
        String times[];
        int images[];

        CustomAdapter(Context c,String subjectName[],String facultyName[],String time[],int image[]){
            super(c, R.layout.list_items, R.id.subjectName, subjectName);
            this.context = c;
            this.subjects=subjectName;
            this.facultyName=facultyName;
            this.times = time;
            this.images = image;
        }




        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View list_item = layoutInflater.inflate(R.layout.list_items, parent, false);
            ImageView image = list_item.findViewById(R.id.image);
            TextView subject = list_item.findViewById(R.id.subjectName);
            TextView faculty = list_item.findViewById(R.id.facultyName);
            TextView time = list_item.findViewById(R.id.time);

            image.setImageResource(images[position]);
            subject.setText(subjects[position]);
            faculty.setText(facultyName[position]);
            time.setText(times[position]);




            return list_item;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this,ShowAllClass.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}