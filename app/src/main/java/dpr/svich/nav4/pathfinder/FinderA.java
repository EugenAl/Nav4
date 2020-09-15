package dpr.svich.nav4.pathfinder;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import androidx.collection.SparseArrayCompat;

/**
 * Класс для поиска пути по алгоритму А*
 */
class FinderA extends Finder{

    /**
     * Функция поиска пути по алгоритму А*
     * Возвращает null если путь не найден, иначе возвращается список вершин.
     * @param startId стартовая позиция
     * @param goalId конечная позиция
     * @return возврашается список вершин, являющихся путем от начальной до конечной вершины
     */
    @Override
    public List<Vertex> findPath(int startId, int goalId){
        Vertex start = vertexes.get(startId);
        Vertex goal = vertexes.get(goalId);
        // существование пути
        boolean isPath = true;
        // множество рассмотренных вершин
        List<Vertex> path = new ArrayList<>();
        // множество, сортированное по меньшему значению эвристической функции
        SortedSet<Vertex> Q = new TreeSet<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return Double.compare(o1.getHeuristicFunction(), o2.getHeuristicFunction());
            }
        });
        // инициализация алгоритма (установка пройденого пути и расчет эвристики)
        start.setG(0);
        start.setHeuristicFunction(start.getG() + heuristic(start, goal));
        Q.add(start);
        // цикл по множеству вершин, которые необходимо просмотреть
        while (!Q.isEmpty()){
            // вершина с наименьшей эвристикой из множества
            Vertex current = Q.first();
            // найдена конечная точка маршрута
            if(current.equals(goal)){
                break;
            }
            // просмотр текущей вершины
            Q.remove(current);
            path.add(current);
            // просмотр смежных вершин текущей вершины
            for(Vertex v : current.getAdjacentVertex()){
                // оценка пройденого пути
                double tentativeScore = current.getG() + distance(current, v);
                if(path.contains(v) && tentativeScore >= v.getG())
                    continue;
                if(!path.contains(v) || tentativeScore < v.getG() || v.equals(goal)){
                    // установка id предыдущей вершины маршрута
                    v.setParentId(current.getId());
                    // установка пройденого пути
                    v.setG(tentativeScore);
                    // расчет эвристической функции
                    v.setHeuristicFunction(v.getG() + heuristic(v, goal));
                    Q.add(v);
                }
            }
            if(Q.isEmpty()) isPath = false;
        }
        if (isPath){
            // найденный маршрут
            List<Vertex> findPath = new ArrayList<>();
            // добавление конечной вершины
            findPath.add(goal);
            // поиск промежуточных вершин до начальной
            Vertex vertex = vertexes.get(goal.getParentId());
            while(vertex.getParentId() != 0){
                findPath.add(vertex);
                vertex = vertexes.get(vertex.getParentId());
            }
            // добавление начальной вершины
            findPath.add(vertex);
            // реверс списка
            Collections.reverse(findPath);
            return findPath;
        }
        return null;
    }

    private double heuristic(Vertex o1, Vertex o2){
        return Math.sqrt(Math.pow((o1.getXDistance() - o2.getXDistance()), 2) +
                Math.pow((o1.getYDistance() - o2.getYDistance()), 2));
    }
}
