package com.example.educare;

        import android.content.Context;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

        import static com.example.educare.R.id.activity_main_timeEditText;

public class ClassForm extends AppCompatActivity {
    private Button addCourseBtn;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_form);

        dbHelper = new DbHelper(this);

        EditText subjectEditText = findViewById(R.id.activity_main_subjectEditText);
        EditText dayEditText = findViewById(R.id.activity_main_dayEditText);
        EditText timeEditText = findViewById(activity_main_timeEditText);
        EditText teacherNameEditText = findViewById(R.id.activity_main_teacherNameEditText);
        Button addCourseBtn= findViewById(R.id.activity_main_loginButton);



        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String SubjectName = subjectEditText.getText().toString();
                String _Day = dayEditText.getText().toString();
                String _Time = timeEditText.getText().toString();
                String _TeacherName = teacherNameEditText.getText().toString();
                System.out.println(SubjectName+_Day+_TeacherName+_Time);

                // validating if the text fields are empty or not.
                if (SubjectName.isEmpty() && _Day.isEmpty() && _Time.isEmpty() && _TeacherName.isEmpty()) {
                    Toast.makeText(ClassForm.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }



                try{
                    dbHelper.insertNewClass(SubjectName,_TeacherName,_Time,_Day);
                    boolean isEmpty =  dbHelper.isEmpty();
                    if(isEmpty){
                        Log.i("Inserting ","Not Inserted");
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(ClassForm.this, MainActivity.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Class Scheduled Successfully",Toast.LENGTH_LONG).show();
                        Log.i("Inserting "," Inserted");

                    }




                }catch (Exception e){
                    Log.i("Create Entry : ", e.toString());
                }




            }
        });

    }

}

