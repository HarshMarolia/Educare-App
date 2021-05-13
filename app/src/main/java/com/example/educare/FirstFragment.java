package com.example.educare;

        import android.content.Context;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    Context thiscontext;
    ListView lv;
    String subjectsName[] = {"Algorithm","Computer Networs","Life Skills","UI/UX Lab","Mad Lab"};
    String facultyName[] = {"Dr. Ratnesh Litoria","Dr. Mahesh Kumar","Dr. Rachna Chaturvedi","Dr. Dinesh Verma","Dr.Prateek Pandey"};
    int images[] = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img1};



    String times[]={"11:00 AM" , "1:00 PM","11:00 AM","11:00 AM","11:00 AM"};
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {


        super.onAttach(context);


        thiscontext = context;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



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
                LayoutInflater layoutInflater = (LayoutInflater)thiscontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View list_item = layoutInflater.inflate(R.layout.list_items, parent, false);
                ImageView image = list_item.findViewById(R.id.image);
                TextView subject = list_item.findViewById(R.id.subjectName);
                TextView faculty = list_item.findViewById(R.id.facultyName);
                TextView time = list_item.findViewById(R.id.time);

                // now set our resources on views
                image.setImageResource(images[position]);
                subject.setText(subjects[position]);
                faculty.setText(facultyName[position]);
                time.setText(times[position]);




                return list_item;
            }



        }
        lv = getActivity().findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(getActivity(), subjectsName, facultyName, times,images);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(getActivity(), "Facebook Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(getActivity(), "Whatsapp Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(getActivity(), "Twitter Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(getActivity(), "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(getActivity(), "Youtube Description", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }




}