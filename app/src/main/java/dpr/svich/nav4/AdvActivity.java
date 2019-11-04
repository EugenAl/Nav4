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

        FinderA finderA = new FinderA();
        finderA.findPath(1, 9);
    }
}
