package cs.bham.ac.uk.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private SharedPreferences sharedPref;


    private ArrayList<FoodData>myFoodList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, type;
        public CardView card;
        public ImageButton fave;
        public ViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.tvTitle);
            time = (TextView)view.findViewById(R.id.tvTime);
            type = (TextView)view.findViewById(R.id.tvType);
            card = (CardView) view.findViewById(R.id.myCardView);
            fave = (ImageButton)view.findViewById(R.id.button);
        }
    }

    public MyAdapter(ArrayList<FoodData> products, SharedPreferences sf) {
        this.myFoodList =products;
        sharedPref = sf;
    }

    @Override

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //holder.fave.setImageResource(R.drawable.ic_star_black_24dp);
        holder.title.setText(myFoodList.get(position).getMealName());
        holder.time.setText(Integer.toString(myFoodList.get(position).getTime())+" mins");
        holder.type.setText(myFoodList.get(position).getMealType());
        if(sharedPref.getBoolean("apiID"+myFoodList.get(position).getApiID(), false))
            holder.fave.setImageResource(R.drawable.ic_star_black_24dp);
        else
            holder.fave.setImageResource(R.drawable.ic_star_border_black_24dp);
        holder.fave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton fave = (ImageButton)v.findViewById(R.id.button);
                SharedPreferences.Editor editor = sharedPref.edit();
                if(sharedPref.getBoolean("apiID"+myFoodList.get(position).getApiID(), false))
                {
                    fave.setImageResource(R.drawable.ic_star_border_black_24dp);
                    editor.putBoolean("apiID"+myFoodList.get(position).getApiID(), false);
                }

                else{
                    fave.setImageResource(R.drawable.ic_star_black_24dp);
                    editor.putBoolean("apiID"+myFoodList.get(position).getApiID(), true);
                }
                editor.apply();


                // update the fav list

            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), DetailActivity.class);
                intent.putExtra("apiID", myFoodList.get(position).getApiID());
                intent.putExtra("Name", myFoodList.get(position).getMealName());
                intent.putExtra("Type", myFoodList.get(position).getMealType());
                intent.putExtra("Time", myFoodList.get(position).getTime());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}




