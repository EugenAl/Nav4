package dpr.svich.nav4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import dpr.svich.nav4.pathfinder.PathAnalise;
import dpr.svich.nav4.pathfinder.PathItem;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdvActivity extends AppCompatActivity {

    private TextView nextCard;
    private TextView topCard;
    private int currentPosition;
    private List<PathItem> pathItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);

        MotionLayout motionLayout = findViewById(R.id.motionLayout);
        nextCard = findViewById(R.id.nextCardTextView);
        topCard = findViewById(R.id.topCardTextView);

        // get arguments
        Bundle args = getIntent().getExtras();
        int posS = args.getInt("positionS");
        int posD = args.getInt("positionD");
        final PathAnalise pathAnalise = new PathAnalise(posS,posD);
        pathItems = pathAnalise.getPathList();
        setTopCard(pathItems.get(currentPosition));

        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int endId) {
                if(endId == R.id.next && currentPosition+1<pathItems.size()){
                    setNextCard(pathItems.get(currentPosition+1));
                } else if(endId == R.id.prev && currentPosition > 0){
                    setNextCard(pathItems.get(currentPosition-1));
                }
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if(i == R.id.offScreenNext && currentPosition < pathItems.size()-1){
                    setTopCard(pathItems.get(++currentPosition));
                } else if(i == R.id.offScreenPrev && currentPosition > 0){
                    setTopCard(pathItems.get(--currentPosition));
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }


    private void setTopCard(PathItem item){
        TextView descriptionTextView = findViewById(R.id.topCardTextView);
        TextView lengthTextView = findViewById(R.id.topCardLengthView);
        ImageView descriptionImageView = findViewById(R.id.imageView);

        descriptionTextView.setText(item.getDescription());
        lengthTextView.setText(item.getDistance() +" m");
        descriptionImageView.setImageResource(item.getImageResId());
    }

    private void setNextCard(PathItem item){
        TextView descriptionTextView = findViewById(R.id.nextCardTextView);
        TextView lengthTextView = findViewById(R.id.nextCardLengthView);
        ImageView descriptionImageView = findViewById(R.id.imageView2);

        descriptionTextView.setText(item.getDescription());
        lengthTextView.setText(item.getDistance() +" m");
        descriptionImageView.setImageResource(item.getImageResId());
    }
}
