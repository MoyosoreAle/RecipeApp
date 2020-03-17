package cs.bham.ac.uk.assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ArrayList<FoodData> myFoodList = new ArrayList<FoodData>();;
    private MyAdapter productsAdpt;
    private RecyclerView.LayoutManager layoutManager;
    public TextView foodName, foodTime, foodType;

    private SharedPreferences sharedPreferences;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        productsAdpt = new MyAdapter(myFoodList, sharedPreferences);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this.getContext());
        listView.setLayoutManager(layoutManager);

        listView.setAdapter(productsAdpt);

        onRequestProducts();

        // Inflate the layout for this fragment
        return view;
    }


    public void onRequestProducts(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, "https://www.sjjg.uk/eat/food-items?prefer=" + sharedPreferences.getString("Meal Type", "Lunch") + "&ordering=" + sharedPreferences.getString("Time", "asc"),null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        populateList(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );
        requestQueue.add(getRequest);
    }

    private void populateList(JSONArray items){
        myFoodList.clear();
        foodName = (TextView)getActivity().findViewById(R.id.tvTitle);
        foodTime = (TextView)getActivity().findViewById(R.id.tvTime);
        foodType = (TextView)getActivity().findViewById(R.id.tvType);
        try{
            for (int i =0; i<items.length();i++) {
                JSONObject jo = items.getJSONObject(i);
                myFoodList.add(new FoodData(jo.getInt("id"), jo.getString("name"),jo.getString("meal"), jo.getInt("time")));

            }
        }
        catch(JSONException err){}
        productsAdpt.notifyDataSetChanged();
    }
}
