package dpr.svich.nav4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import dpr.svich.nav4.R;
import dpr.svich.nav4.entity.Room;

/**
 * Custom adapter for RecyclerView source and destination points
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>{

    private List<Room> roomList;
    private Context context;

    /**
     * View holder class
     */
    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public LinearLayout constraintLayout;
        public CustomViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.item_text);
            constraintLayout = view.findViewById(R.id.item_layout);
        }
    }

    public RecyclerAdapter(Context context, List<Room> rooms){
        roomList = rooms;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Room room = roomList.get(position);
        // Set item label
        holder.textView.setText(room.getLabel());
        switch(room.getType()){
            case LECTYRE:
                // Set item background
                holder.constraintLayout.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.item_shape_green));
                // Set item text color
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                break;
            case TOILET:
                holder.constraintLayout.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.item_shape_indigo));
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorIndigo));
                break;
            case ADMINISTRATION:
                holder.constraintLayout.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.item_shape_deeporange));
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorDeepOrange));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.point_recycler_item, parent, false);
        return new CustomViewHolder(v);
    }
}
