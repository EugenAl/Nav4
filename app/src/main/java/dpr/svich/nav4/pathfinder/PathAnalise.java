package dpr.svich.nav4.pathfinder;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import dpr.svich.nav4.R;

public class PathAnalise {

    private List<Vertex> path;
    private FinderA finderA;

    public PathAnalise(int positionSource, int positionDestination) {
        finderA = new FinderA();
        path = finderA.findPath(positionSource, positionDestination);
    }

    /**
     * Функция, возвращающая список путевых точек для отображения пользователю
     *
     * @return список путевых точек
     */
    @SuppressLint("DefaultLocale")
    public List<PathItem> getPathList() {
        List<PathItem> pathItems = new ArrayList<>();
        if(path == null) return null;
        for (int i = 0; i < path.size(); i++) {
            // найдены элементы коридора
            if (path.get(i).getName().startsWith("ph")) {
                PathItem corridor = new PathItem();
                boolean direction = true;
                boolean bowl = false;
                int startDirection = Integer.parseInt(path.get(i).getName().split("_")[1]);
                // сбор информации о расстоянии коридора
                i++;
                while (path.get(i).getName().startsWith("ph")) {
                    int currentDirection = Integer.parseInt(path.get(i).getName().split("_")[1]);
                    if(startDirection < currentDirection){
                        direction = true;
                    } else {
                        direction = false;
                    }
                    if(currentDirection == 9) bowl = true;
                    corridor.setDistance(corridor.
                            getDistance() + finderA.distance(path.get(i - 1), path.get(i)));
                    i++;
                }
                corridor.setDistance(corridor.
                        getDistance() + finderA.distance(path.get(i - 1), path.get(i)));

                if(path.get(i).getName().startsWith("st")){
                    corridor.setDescription("Направляйтесь по коридору к " +
                            (path.get(i).getName().split("_")[1].equals("1")?
                                    "лестнице, со стороны входа": "дальней от входа лестнице"));
                } else {
                    corridor.setDescription("Направляйтесь по коридору к " + path.get(i).getName());
                }
                if (direction){
                    corridor.setImageResId(R.drawable.to_right);
                } else{
                    corridor.setImageResId(R.drawable.to_left);
                }
                if (bowl){
                    corridor.setImageResId(R.drawable.to_bowl);
                    corridor.setDescription("Направляйтесь по коридору к проходу " +
                            "возле лестницы со стороны входа");
                }
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
                String floorWord = "";
                switch (floor){
                    case 1: floorWord = "один";break;
                    case 2: floorWord = "два";break;
                    case 3: floorWord = "три";break;
                }
                stairs.setDistance(stairs.
                        getDistance() + finderA.distance(path.get(i - 1), path.get(i)));
                stairs.setDescription(String.format("%s на %s %s", dir, floorWord,
                        (floor == 1) ? "этаж" : "этажа"));
                stairs.setImageResId(R.drawable.stairs);
                pathItems.add(stairs);
            }
            if (!path.get(i).getName().startsWith("s")&&!path.get(i).getName().startsWith("p")){
                PathItem point = new PathItem();
                String goal = i == path.size()-1 ? "Вы прибыли в пункт назначения.":"Вы находитесь";
                point.setDescription(goal + " " + path.get(i).getName());
                point.setImageResId(R.drawable.destination);
                pathItems.add(point);
            }
        }
        return pathItems;
    }
}
