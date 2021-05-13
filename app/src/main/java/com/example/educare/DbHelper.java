package com.example.educare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="EDMTDev";
    private static final int DB_VER = 1;
    public static final String DB_TABLE_TASK="Task";
    public static final String DB_COLUMN="TaskName";

    public static final String DB_TABLE_CLASS="Class";
    public static final String DB_COLUMN_SUBJECT="Subject";
    public static final String DB_COLUMN_DAY="Day";
    public static final String DB_COLUMN_TIME="Time";
    public static final String DB_COLUMN_Faculty="Faculty";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_task = String.format("CREATE TABLE IF NOT EXISTS %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", DB_TABLE_TASK,DB_COLUMN);
        String query_create_class = String.format("CREATE TABLE IF NOT EXISTS %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);", DB_TABLE_CLASS,DB_COLUMN_SUBJECT,DB_COLUMN_Faculty,DB_COLUMN_TIME,DB_COLUMN_DAY);
        db.execSQL(query_create_task);
        db.execSQL(query_create_class);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_db_task_del = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE_TASK);
        String query_db_class_del = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE_TASK);
    }

    public void insertNewTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN, task);
        db.insertWithOnConflict(DB_TABLE_TASK,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE_TASK,DB_COLUMN + " = ?", new String[]{task});
        db.close();
    }


    public void deleteClass(String subject,String time){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(DB_TABLE_CLASS,DB_COLUMN_SUBJECT +  "="+ subject , null);
        db.delete(DB_TABLE_CLASS,"Subject=? and Time=?",new String[]{subject,time});
        db.close();
    }


    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE_TASK, new String[]{DB_COLUMN},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_COLUMN);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public void insertNewClass(String subject,String faculty,String time, String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_SUBJECT, subject);
        values.put(DB_COLUMN_Faculty,faculty);
        values.put(DB_COLUMN_TIME,time);
        values.put(DB_COLUMN_DAY,day);

        db.insertWithOnConflict(DB_TABLE_CLASS,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public boolean isEmpty(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE_CLASS, new String[]{DB_COLUMN_SUBJECT,DB_COLUMN_Faculty,DB_COLUMN_DAY,DB_COLUMN_TIME},null,null,null,null,null);
        if(cursor.getCount() == 0)
            return  true;
        else return  false;
    }


    public int getCount(String _day){
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE_CLASS, new String[]{DB_COLUMN_SUBJECT,DB_COLUMN_Faculty,DB_COLUMN_DAY,DB_COLUMN_TIME},null,null,null,null,null);
        while (cursor.moveToNext()){
            int sub_index = cursor.getColumnIndex(DB_COLUMN_SUBJECT);
            int faculty_index = cursor.getColumnIndex(DB_COLUMN_Faculty);
            int time_index = cursor.getColumnIndex(DB_COLUMN_TIME);
            int day_index = cursor.getColumnIndex(DB_COLUMN_DAY);

            String subject = cursor.getString(sub_index);
            String faculty = cursor.getString(faculty_index);
            String time = cursor.getString(time_index);
            String day = cursor.getString(day_index);

            Log.i("Subject:",subject);

            if(_day.equals(day)){
                total+=1;
            }

        }
        return total;
    }

    public ArrayList<MyClasses> fetchAllClass(String _day){

        ArrayList<MyClasses> allClasses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE_CLASS, new String[]{DB_COLUMN_SUBJECT,DB_COLUMN_Faculty,DB_COLUMN_DAY,DB_COLUMN_TIME},null,null,null,null,null);
        Log.i("fetchAllClasses: ",cursor.getColumnName(0));
        if(cursor.getCount() == 0)
            Log.i("fetchAllClasses: ","EMPTY DB");

        while (cursor.moveToNext()){
            int sub_index = cursor.getColumnIndex(DB_COLUMN_SUBJECT);
            int faculty_index = cursor.getColumnIndex(DB_COLUMN_Faculty);
            int time_index = cursor.getColumnIndex(DB_COLUMN_TIME);
            int day_index = cursor.getColumnIndex(DB_COLUMN_DAY);

            String subject = cursor.getString(sub_index);
            String faculty = cursor.getString(faculty_index);
            String time = cursor.getString(time_index);
            String day = cursor.getString(day_index);

            Log.i("Subject:",subject);

            if(_day.equals(day)){
                MyClasses mc = new MyClasses(subject,faculty,day,time);
                allClasses.add(mc);
            }

        }
        cursor.close();
        db.close();
        return allClasses;

    }



}
