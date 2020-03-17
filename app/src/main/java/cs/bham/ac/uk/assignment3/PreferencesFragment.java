package cs.bham.ac.uk.assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment {

    private String mealType;
    private String Time;
    private SharedPreferences sharedPreferences;

    public PreferencesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        if(savedInstanceState != null)
        {
            mealType = savedInstanceState.getString("Meal Type");
            Time = savedInstanceState.getString("Time");
        }

        View v = inflater.inflate(R.layout.fragment_preferences, container, false);


        RadioButton rb = v.findViewById(R.id.radioDinner);
        RadioButton rb1 = v.findViewById(R.id.radioLunch);
        RadioButton rb2 = v.findViewById(R.id.radioBreakfast);
        RadioButton rb3 = v.findViewById(R.id.radioHigh);
        RadioButton rb4 = v.findViewById(R.id.radioLow);


        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Meal Type", "Dinner");
                editor.commit();
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Meal Type", "Lunch");
                editor.commit();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Meal Type", "Breakfast");
                editor.commit();
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Time", "desc");
                editor.commit();
            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Time", "asc");
                editor.commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }


    public void onSaveInstanceState(Bundle b)
    {
        b.putString("Meal Type", mealType);
        b.putString("Time", Time);
        System.out.println("Saving state");
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        RadioGroup rg = getView().findViewById(R.id.radio);
        RadioGroup rg2 = getView().findViewById(R.id.radio2);

        switch(((MainActivity)getActivity()).mealType)
        {
            case "Breakfast":
            {
                rg2.check(R.id.radioBreakfast);
                break;
            }
            case "Lunch":
            {
                rg2.check(R.id.radioLunch);
                break;
            }
            case "Dinner":
            {
                rg2.check(R.id.radioDinner);
                break;
            }
        }

        switch(((MainActivity)getActivity()).Time)
        {
            case "desc":
            {
                rg.check(R.id.radioHigh);
                break;
            }
            case "asc":
            {
                rg.check(R.id.radioLow);
                break;
            }

        }

    }

}

