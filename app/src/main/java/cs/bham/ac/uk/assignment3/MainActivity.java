package cs.bham.ac.uk.assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ArrayList<FoodData>myFoodList = new ArrayList<FoodData>();;
    private MyAdapter productsAdpt;
    private RecyclerView.LayoutManager layoutManager;
    FavouritesFragment sF = new FavouritesFragment();
    PreferencesFragment pF = new PreferencesFragment();
    HomeFragment hF = new HomeFragment();
    FragmentManager fm = getSupportFragmentManager();
    TextView foodName, foodTime, foodType;
    public String mealType;
    public String Time;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((BottomNavigationView)findViewById(R.id.botttomNav)).setOnNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.frag_frame, hF).commit();
        }

        sharedPreferences  = this.getPreferences(Context.MODE_PRIVATE);
        // Radios - meal preference
        mealType = sharedPreferences.getString("Meal Type", "Breakfast");
        System.out.println("Preference for meals:" + mealType);
            // if we have time (favour long recipes) or not
        Time = sharedPreferences.getString("Time", "asc");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    { getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true; }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fT = fm.beginTransaction();
        switch (item.getItemId())
        {
            case R.id.home:
                fT.replace(R.id.frag_frame, hF);
                break;
            case R.id.preferences:
                fT.replace(R.id.frag_frame, pF);
                break;
            case R.id.favorites:
                fT.replace(R.id.frag_frame, sF);
                break;
        }
        fT.commit();
        return true;
    }

}

