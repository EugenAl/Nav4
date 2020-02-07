package dpr.svich.nav4.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class PathAnaliz {

    private List<Vertex> path;

    public PathAnaliz(int positionSourse, int positionDestination) {
        FinderA finderA = new FinderA();
        path = finderA.findPath(positionSourse, positionDestination);
    }

    /**
     * Функция, возвращающая список путевых точек для отображения пользователю
     *
     * @return список путевых точек
     */
    private List<PathItem> getPathList() {
        List<PathItem> pathItems = new ArrayList<>();
        double distance = 0;
        for (Vertex v : path) {
            if(!v.getName().startsWith("p") && !v.getName().startsWith("s")){
                pathItems.add(new PathItem(v.getName(), distance, 15));
            }
        }
        return null;
    }
}
