package com.example.nutripets;


import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    TextView next;
    TextView back;
    ViewPager viewPager;
    EditText fruits;
    EditText veggies;
    DatabaseHelper db;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("created1");
        db = new DatabaseHelper(getActivity());
//        SQLiteDatabase readable = db.getReadableDatabase();
//        long count = DatabaseUtils.queryNumEntries(readable, "user");
//        System.out.println("The count was" + count);
//
//        String query = "SELECT * FROM " + db.TABLE_NAME;
//        Cursor cursor = readable.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            String gender = cursor.getString(cursor.getColumnIndex(db.COL2));
//            System.out.println("Retrieved gender1 " + gender);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        System.out.println("created2");
//        db = new DatabaseHelper(getActivity());
//        SQLiteDatabase readable = db.getReadableDatabase();
//        long count = DatabaseUtils.queryNumEntries(readable, "user");
//        System.out.println("The count was" + count);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);

        // Get fruits EditText field
        fruits = (EditText) view.findViewById(R.id.fruitsCount);
        fruits.setText("2");
        fruits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String fruitsEntry = fruits.getText().toString();
                db.addToTable(db.COL4, fruitsEntry);
                System.out.println("ENTERED FRUITS: " + fruitsEntry);
            }
        });

        // Get veggies EditText field
        veggies = (EditText) view.findViewById(R.id.veggiesCount);
        veggies.setText("3");
        veggies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String veggiesEntry = veggies.getText().toString();
                db.addToTable(db.COL5, veggiesEntry);
                System.out.println("ENTERED VEGGIES: " + veggiesEntry);
            }
        });

        // Next button
        next = view.findViewById(R.id.slideTwoNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        back = view.findViewById(R.id.slideTwoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        return view;
    }

}
