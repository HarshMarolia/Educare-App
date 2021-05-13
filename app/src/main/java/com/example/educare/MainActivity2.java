package com.example.educare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.educare.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    ListView lv;
    ImageView noList;
    String subjectsName[];
    String facultyName[];
    int images[];
    String times[];
    DbHelper dbHelper ;

    ArrayList<MyListData> allListData = new ArrayList<>();

    MyListData[] myListData;

//    int dayOfWeek=Integer.parseInt(getIntent().getStringExtra("id"));
    int dayOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        dayOfWeek=Integer.parseInt(getIntent().getStringExtra("id"));
        Calendar calendar = Calendar.getInstance();


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



        int c = dbHelper.getCount(day);



        myListData = new MyListData[c];
        mapItems(day);

        setContentView(R.layout.activity_week_wise_time);









        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerViewid);
        adapter _adapter = new adapter(myListData,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(_adapter);







    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
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

                String subName= allClass.get(i).subjectName;
                String time= allClass.get(i).time;
                String dayDb= allClass.get(i).day;
                String facName = allClass.get(i).facultyName;
                int thisImage = R.drawable.img1;



                if(imgIndex >= 5)
                    imgIndex = 1;

                else if(imgIndex == 1)
                    thisImage= R.drawable.img1;
                else if(imgIndex == 2)
                    thisImage = R.drawable.img2;
                else if(imgIndex == 3)
                    thisImage= R.drawable.img3;
                else
                    thisImage= R.drawable.img4;

                imgIndex +=1;


                MyListData thisListData = new MyListData(subName,facName,time,thisImage,String.valueOf(dayOfWeek));
                myListData[i] = thisListData;





            }
        }catch (Exception e){
            Log.i("Exception:",e.toString());
        }
    }
}