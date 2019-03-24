package com.example.nutripets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    TextView next;
    ViewPager viewPager;
    DatabaseHelper db;
    EditText weightInput;

    public FirstFragment() {
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
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);

        // Gender Spinner
        Spinner genderSpinner = (Spinner) view.findViewById(R.id.genderSpinner);
        genderSpinner.setPrompt("");
        String[] genders = new String[]{" ", "Female", "Male"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, genders );
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
            // Gender spinner listener to store value
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String genderSelected = (String) adapterView.getItemAtPosition(i);
                System.out.println("SELECTED GENDER: " + genderSelected);
                db.addToTable(db.COL2, genderSelected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Age Spinner
        Spinner ageSpinner = (Spinner) view.findViewById(R.id.ageSpinner);
        ageSpinner.setPrompt("Please");
        String[] ages = new String[]{ " ", "19-30", "31-50", "51+"};
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, ages );
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ageSelected = (String) adapterView.getItemAtPosition(i);
                System.out.println("SELECTED AGE: " + ageSelected);
                db.addToTable(db.COL3, ageSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Activities Spinner
        Spinner activitySpinner = (Spinner) view.findViewById(R.id.activitySpinner);
        activitySpinner.setPrompt("");
        String[] activityLevels = new String[]{" ", "No activity","Low Activity (1-2 times per week)",
                "Moderate Activity (3-4 times per week)", "High Activity (5+ times per week)"};
        ArrayAdapter<String> activityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, activityLevels );
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activityAdapter);

        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String activitySelected = (String) adapterView.getItemAtPosition(i);
                System.out.println("SELECTED ACTIVITY: " + activitySelected);
                if (activitySelected.startsWith("No")) {
                    db.addToTable(db.COL6, "N");
                } else if (activitySelected.startsWith("Low")) {
                    db.addToTable(db.COL6, "L");
                } else if (activitySelected.startsWith("Moderate")) {
                    db.addToTable(db.COL6, "M");
                } else {
                    db.addToTable(db.COL6, "H");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Next Button
        next = view.findViewById(R.id.slideOneNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        return view;
    }
}
