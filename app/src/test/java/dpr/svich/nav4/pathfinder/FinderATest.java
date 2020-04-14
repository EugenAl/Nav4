package dpr.svich.nav4.pathfinder;

import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class FinderATest {

    private FinderA finderA;
    private Integer startPoint;
    private Integer goalPoint;

    @Before
    public void init(){
        finderA = new FinderA();
    }

    public FinderATest(Integer startPoint, Integer goalPoint){
        this.startPoint = startPoint;
        this.goalPoint = goalPoint;
    }

    @Parameterized.Parameters
    public static Collection primePoints(){
        return Arrays.asList(new Object[][]{
                {1, 9},
                {1, 31},
                {31, 9},
                {40, 20},
                {8, 15},
                {39, 16},
                {18, 39}
        });
    }

    @Test
    public void findPath() {
        List<Vertex> list;
        assertNotNull(list = finderA.findPath(startPoint, goalPoint));
        System.out.println("Path length: "+list.size());
    }
}