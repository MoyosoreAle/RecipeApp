 package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

 public class DetailActivity extends AppCompatActivity {
    //TextView foodName, foodDescription, foodIngredients, foodSteps;
     private int apiID, Time;
     private String Name, Type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        apiID = getIntent().getIntExtra("apiID", 0);
        Name = getIntent().getStringExtra("Name");
        Type = getIntent().getStringExtra("Type");
        Time = getIntent().getIntExtra("Time", 0);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, " https://www.sjjg.uk/eat/recipe-details/" + apiID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView foodDescription = (TextView)findViewById(R.id.txtDescription);
                        TextView foodIngredients = (TextView)findViewById(R.id.txtIngredients);
                        TextView foodName = (TextView)findViewById(R.id.txtRecipeName);
                        TextView foodSteps = (TextView)findViewById(R.id.txtSteps);
                        TextView foodType = (TextView)findViewById(R.id.txtType);
                        TextView foodTime = (TextView)findViewById(R.id.txtTime);
                        try {
                            foodDescription.setText(response.getString("description"));
                            //foodSteps.setText(response.getString("steps"));
                            String steps = "";
                            for (int i = 0; i < response.getJSONArray("steps").length(); i++) {
                                steps += response.getJSONArray("steps").getString(i)+"\n";
                            }
                            foodSteps.setText(steps);

                            String ingredients = "";
                            for (int i = 0; i < response.getJSONArray("ingredients").length(); i++) {
                                ingredients += response.getJSONArray("ingredients").getString(i)+"\n";
                            }
                            foodIngredients.setText(ingredients);

                            foodName.setText(Name);
                            foodType.setText(Type);
                            foodTime.setText(Integer.toString(Time)+" mins");
                        } catch (JSONException err) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(getRequest);
    }

 }