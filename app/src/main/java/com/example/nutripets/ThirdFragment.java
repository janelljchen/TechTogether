package com.example.nutripets;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ThirdFragment extends Fragment {

    TextView done;
    TextView back;
    ViewPager viewPager;
    DatabaseHelper db;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);

        // Pet Image (Bunny)
        final ImageView image= view.findViewById(R.id.bunny);
        image.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    image.setColorFilter(Color.argb(100,0,0,0));
                }
                if (event.getAction()== MotionEvent.ACTION_UP){
                    image.setColorFilter(Color.argb(100,0,0,0));
                    db.addToTable(db.COL7, "bunny");
                    System.out.println("ANIMAL SELECTED: bunny");
                }

                return false;
            }
        });
        back = view.findViewById(R.id.slideThreeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });


        done = view.findViewById(R.id.slideThreeDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "DONE", Toast.LENGTH_LONG).show();
                OpenMainPage();
            }
        });

        return view;
    }
    public void OpenMainPage(){
        db = new DatabaseHelper(getActivity());
        SQLiteDatabase readable = db.getReadableDatabase();
        SQLiteDatabase writable = db.getWritableDatabase();
        Intent intent= new Intent(getActivity(), Mainpage.class);

        String query = "SELECT * FROM " + db.TABLE_NAME;
        Cursor cursor = readable.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String gender = cursor.getString(cursor.getColumnIndex(db.COL2));
            String age = cursor.getString(cursor.getColumnIndex(db.COL3));
            String fruits = cursor.getString(cursor.getColumnIndex(db.COL4));
            String vegetables = cursor.getString(cursor.getColumnIndex(db.COL5));
            String activity = cursor.getString(cursor.getColumnIndex(db.COL6));
            String pet = cursor.getString(cursor.getColumnIndex(db.COL7));
            String balance = cursor.getString(cursor.getColumnIndex(db.COL8));

            System.out.println("Fruits: " + fruits);
            System.out.println("Veggies: " + vegetables);
            if (fruits == null) {
                intent.putExtra("fruits", 2);
                System.out.println("put in default fruits");
            } else {

                intent.putExtra("fruits", Integer.parseInt(fruits));
            }
            if (vegetables == null) {
                intent.putExtra("vegetables", 3);
                System.out.println("put in default veggies");
            } else {
                intent.putExtra("vegetables", Integer.parseInt(vegetables));
            }

        }

        writable.execSQL("DELETE FROM " + db.TABLE_NAME);
        readable.close();
        writable.close();
        db.close();



        startActivity(intent);
        getActivity().finish();
    }

}
