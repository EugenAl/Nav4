package dpr.svich.nav4;

import androidx.appcompat.app.AppCompatActivity;
import dpr.svich.nav4.pathfinder.FinderA;
import dpr.svich.nav4.pathfinder.Vertex;

import android.os.Bundle;

public class AdvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);

        // get arguments
        Bundle args = getIntent().getExtras();
        int posS = args.getInt("positionS");
        int posD = args.getInt("positionD");
        // path finder init and find path
        FinderA finderA = new FinderA();
        finderA.findPath(posS, posD);
    }
}
