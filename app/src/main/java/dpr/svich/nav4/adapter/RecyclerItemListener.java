package dpr.svich.nav4.adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemListener implements RecyclerView.OnItemTouchListener {

    private RecyclerTouchListener listener;
    private GestureDetector gd;

    public interface RecyclerTouchListener{
        public void onClickItem(View v, int position);
        public void onLongClickItem(View v, int position);
    }

    public RecyclerItemListener(Context context, final RecyclerView rv, final RecyclerTouchListener listener) {
        this.listener = listener;
        gd = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                // Find the view
                View v = rv.findChildViewUnder(e.getX(), e.getY());
                // Notify event
                if (v != null) {
                    listener.onLongClickItem(v, rv.getChildAdapterPosition(v));
                }
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View v = rv.findChildViewUnder(e.getX(), e.getY());
                // Notify event
                if (v != null) {
                    listener.onClickItem(v, rv.getChildAdapterPosition(v));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(),e.getY());
        return (child != null && gd.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
