package dpr.svich.nav4.pathfinder;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

public class PathAnaliz {

    private List<Vertex> path;
    private FinderA finderA;

    public PathAnaliz(int positionSourse, int positionDestination) {
        finderA = new FinderA();
        path = finderA.findPath(positionSourse, positionDestination);
    }

    /**
     * Функция, возвращающая список путевых точек для отображения пользователю
     *
     * @return список путевых точек
     */
    @SuppressLint("DefaultLocale")
    private List<PathItem> getPathList() {
        List<PathItem> pathItems = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            // найдены элементы коридора
            if (path.get(i).getName().startsWith("ph")) {
                PathItem corridor = new PathItem();
                // сбор информации о расстоянии коридора
                i++;
                while (path.get(i).getName().startsWith("ph")) {
                    corridor.setDistance(corridor.
                            getDistance() + finderA.distance(path.get(i - 1), path.get(i)));
                    i++;
                }
                corridor.setDistance(corridor.
                        getDistance() + finderA.distance(path.get(i - 1), path.get(i)));

                corridor.setDescription("Направляйтесь по коридору к " + path.get(i).getName());
                pathItems.add(corridor);
            }
            // найдены лестницы
            if (path.get(i).getName().startsWith("st")) {
                PathItem stairs = new PathItem();
                int direction = Integer.parseInt((path.get(i).getName().split("_"))[2]);
                int floor = 0;
                i++;
                while (path.get(i).getName().startsWith("s")) {
                    if (path.get(i).getName().startsWith("sp")) floor++;
                    stairs.setDistance(stairs.
                            getDistance() + finderA.distance(path.get(i - 1), path.get(i)));
                    i++;
                }
                String dir =
                        direction - Integer.parseInt((path.get(i - 1).getName().split("_"))[2])
                                > 0 ? "Спуститесь" : "Поднимитесь";
                stairs.setDistance(stairs.
                        getDistance() + finderA.distance(path.get(i - 1), path.get(i)));
                stairs.setDescription(String.format("%s на %d %s", dir, floor,
                        (floor == 1) ? "этаж" : "этажа"));
                pathItems.add(stairs);
            }
            if (!path.get(i).getName().startsWith("s")&&!path.get(i).getName().startsWith("p")){
                PathItem point = new PathItem();
                String goal = i == path.size()-1 ? "Вы прибыли в пункт назначения.":"";
                point.setDescription(goal + " " + path.get(i).getName());
            }
        }
        return null;
    }
}
