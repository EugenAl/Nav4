package dpr.svich.nav4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import dpr.svich.nav4.pathfinder.PathAnalise;
import dpr.svich.nav4.pathfinder.PathItem;

import android.os.Bundle;
import android.widget.FrameLayout;
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
        // path finder init and find path
        //FinderA finderA = new FinderA();
        //finderA.findPath(posS, posD);
        PathAnalise pathAnalise = new PathAnalise(posS,posD);
        pathItems = pathAnalise.getPathList();
        topCard.setText(pathItems.get(currentPosition).getDescription());

        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int endId) {
                if(endId == R.id.next && currentPosition+1<pathItems.size()){
                    nextCard.setText(pathItems.get(currentPosition+1).getDescription());
                }
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if(i == R.id.offScreenNext){
                    topCard.setText(nextCard.getText());
                    currentPosition++;
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }


}
