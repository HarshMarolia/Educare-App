package com.example.educare;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{

    private final MyListData[] listdata;
    DbHelper dbHelper;
    Context context;
    public adapter(MyListData[] listdata,Context context)
    {
        this.listdata = listdata;
        this.context = context;
        dbHelper = new DbHelper(context);
    }



    @Override

    public adapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder( adapter.ViewHolder holder, int position) {
        final MyListData myListData = listdata[position];

        try {
            holder.textView1.setText(listdata[position].getSubject());
            holder.textView2.setText(listdata[position].getfacultyName());
            holder.textView3.setText(listdata[position].gettime());
            holder.imageView.setImageResource(listdata[position].getImgId());
            holder.imageViewDel.setImageResource(R.drawable.ic_baseline_delete_forever_24);
        }catch (Exception e){
            Log.i("Exception: ",e.toString());
        }
        holder.imageViewDel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Log.i("Removing","test");
                try {
                    dbHelper.deleteClass(listdata[position].getSubject(),listdata[position].gettime());




                    Toast.makeText(view.getContext(),"Deleted...", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context,MainActivity2.class).putExtra("id",listdata[position].getDayId());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    context.startActivity(i);




                }catch (Exception e){
                    Log.i("Error:",e.toString());
                }

            }
        });

    }





    @Override
    public int getItemCount() {
        return listdata.length;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public ImageView imageViewDel;

        public View linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img);
            this.textView1 = (TextView) itemView.findViewById(R.id.subjectName);
            this.textView2= (TextView) itemView.findViewById(R.id.facultyName);
            this.textView3 = (TextView) itemView.findViewById(R.id.time);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            this.imageViewDel  =(ImageView)itemView.findViewById(R.id.imgDel);



        }
    }


}
