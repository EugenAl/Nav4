package dpr.svich.nav4.pathfinder;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс для поиска пути по алгоритму А*
 */
public class FinderA {


    SparseArray<Vertex> vertexes = new SparseArray<>();
    List<Edge> edges = new ArrayList<>();

    private void init() {
        edges.add(new Edge(0, 1, 2, 2d));
        edges.add(new Edge(1, 2, 3, 1.5d));
        edges.add(new Edge(2, 2, 4, 1d));
        edges.add(new Edge(3, 2, 5, 7d));
        edges.add(new Edge(4, 5, 6, 1d));
        edges.add(new Edge(5, 5, 7, 1d));
        edges.add(new Edge(6, 5, 8, 7.5d));
        edges.add(new Edge(7, 8, 9, 1d));
        edges.add(new Edge(8, 8, 10, 10d));
        edges.add(new Edge(9, 10, 11, 1d));
        edges.add(new Edge(10, 10, 12, 1d));
        edges.add(new Edge(11, 10, 13, 2d));
        edges.add(new Edge(12, 13, 14, 1d));
        edges.add(new Edge(13, 13, 15, 4d));
        edges.add(new Edge(14, 15, 17, 3.5d));
        edges.add(new Edge(15, 3 , 16, 5d));
        edges.add(new Edge(16, 16, 18, 5d));
        edges.add(new Edge(17, 18, 19, 1.5d));
        edges.add(new Edge(18, 19, 20, 1d));
        edges.add(new Edge(19, 19, 21, 7d));
        edges.add(new Edge(20, 21, 22, 1d));
        edges.add(new Edge(21, 21, 38, 5d));
        edges.add(new Edge(22, 38, 39, 1d));
        edges.add(new Edge(23, 38, 40, 1d));
        edges.add(new Edge(24, 21, 23, 7d));
        edges.add(new Edge(25, 23, 24, 1d));
        edges.add(new Edge(26, 23, 25, 7d));
        edges.add(new Edge(27, 25, 26, 1d));
        edges.add(new Edge(28, 25, 27, 8d));
        edges.add(new Edge(29, 27, 28, 1d));
        edges.add(new Edge(30, 27, 29, 8d));
        edges.add(new Edge(31, 29, 30, 1d));
        edges.add(new Edge(32, 29, 31, 1d));
        edges.add(new Edge(33, 29, 32, 3d));
        edges.add(new Edge(34, 32, 33, 1d));
        edges.add(new Edge(35, 32, 34, 1d));
        edges.add(new Edge(36, 32, 35, 10d));
        edges.add(new Edge(37, 35, 36, 1d));
        edges.add(new Edge(38, 35, 37, 2d));
        edges.add(new Edge(39, 37, 17, 3.5d));
        edges.add(new Edge(40, 13, 41, 2.5d));
        edges.add(new Edge(41, 35, 42, 2d));


        /*
         * st_x_y - лестница х стороны у этажа
         * sp_x_y - пролет х стороны у этажа
         * phx_y - коридор на х этаже, у сегмент
         */
        vertexes.put(1, new Vertex("Вход", 1, 0));
        vertexes.put(2, new Vertex("ph1_1", 2, 2d));
        vertexes.put(3, new Vertex("st_1_1", 3, 2.5d));
        vertexes.put(4, new Vertex("к.1", 4, 2.1d));
        vertexes.put(5, new Vertex("ph1_2", 5, 9.1d));
        vertexes.put(6, new Vertex("к.2", 6, 9.1d));
        vertexes.put(7, new Vertex("к.5", 7, 9.1d));
        vertexes.put(8, new Vertex("ph1_3", 8, 14.1d));
        vertexes.put(9, new Vertex("к.3", 9, 14.1d));
        vertexes.put(10, new Vertex("ph1_4", 10, 21.1d));
        vertexes.put(11, new Vertex("к.3a", 11, 21.1d));
        vertexes.put(12, new Vertex("к.4", 12, 21.1d));
        vertexes.put(13, new Vertex("ph1_5", 13, 22d));
        vertexes.put(14, new Vertex("Столовая", 14, 22d));
        vertexes.put(15, new Vertex("st_2_1", 15, 24d));
        vertexes.put(16, new Vertex("sp_1_1", 16, 6.5d));
        vertexes.put(17, new Vertex("sp_2_1", 17, 26d));
        vertexes.put(18, new Vertex("st_1_2", 18, 5.1d));
        vertexes.put(19, new Vertex("ph2_1", 19, 5d));
        vertexes.put(20, new Vertex("к.9", 20, 5d));
        vertexes.put(21, new Vertex("ph2_2", 21, 7d));
        vertexes.put(22, new Vertex("к.10", 22, 7.1d));
        vertexes.put(23, new Vertex("ph2_3", 23, 10d));
        vertexes.put(24, new Vertex("к.11", 24, 10.1d));
        vertexes.put(25, new Vertex("ph2_4", 25, 13d));
        vertexes.put(26, new Vertex("к.12", 26, 13.2d));
        vertexes.put(27, new Vertex("ph2_5", 27, 16.2d));
        vertexes.put(28, new Vertex("к.13", 28, 16.5d));
        vertexes.put(29, new Vertex("ph2_6", 29, 18.7d));
        vertexes.put(30, new Vertex("к.17", 30, 19d));
        vertexes.put(31, new Vertex("к.14", 31, 19d));
        vertexes.put(32, new Vertex("ph2_7", 32, 21d));
        vertexes.put(33, new Vertex("к.14а", 33, 21.2d));
        vertexes.put(34, new Vertex("к.16", 34, 21.2d));
        vertexes.put(35, new Vertex("ph2_8", 35, 25d));
        vertexes.put(36, new Vertex("к.15", 36, 25.2d));
        vertexes.put(37, new Vertex("st_2_2", 37, 25.2d));
        vertexes.put(38, new Vertex("ph2_9", 38, 10.2d));
        vertexes.put(39, new Vertex("к.19", 39, 11d));
        vertexes.put(40, new Vertex("к.20", 40, 11.2d));
        vertexes.put(41, new Vertex("М. туалет", 41, 23.2d));
        vertexes.put(42, new Vertex("Ж. туалет", 42, 23.5d));
    }
    public FinderA() {
        init();

        for (Edge edge : edges) {
            vertexes.get(edge.getOutputVertexId()).getAdjacentVertex()
                    .add(vertexes.get(edge.getInputVertexId()));
            vertexes.get(edge.getInputVertexId()).getAdjacentVertex()
                    .add(vertexes.get(edge.getOutputVertexId()));
        }
    }

    /**
     * Функция поиска пути по алгоритму А*
     * Возвращает null если путь не найден, иначе возвращается список вершин.
     * @param startId стартовая позиция
     * @param goalId конечная позиция
     * @return возврашается список вершин, являющихся путем от начальной до конечной вершины
     */
    public List<Vertex> findPath(int startId, int goalId){
        Vertex start = vertexes.get(startId);
        Vertex goal = vertexes.get(goalId);
        // множество рассмотренных вершин
        List<Vertex> path = new ArrayList<>();
        // множество, сортированное по меньшему значению эвристической функции
        SortedSet<Vertex> Q = new TreeSet<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return Double.compare(o1.getHeuristicFunction(), o2.getHeuristicFunction());
            }
        });
        start.setG(0);
        start.setHeuristicFunction(start.getG() + heuristic(start, goal));
        Q.add(start);
        while (!Q.isEmpty()){
            Vertex current = Q.first();
            if(current.equals(goal)){
                break;
            }
            Q.remove(current);
            path.add(current);
            for(Vertex v : current.getAdjacentVertex()){
                double tentativeScore = current.getG() + distance(current, v);
                if(path.contains(v) && tentativeScore >= v.getG())
                    continue;
                if(!path.contains(v) || tentativeScore < v.getG()){
                    v.setParentId(current.getId());
                    v.setG(tentativeScore);
                    v.setHeuristicFunction(v.getG() + heuristic(v, goal));
                    Q.add(v);
                }

            }
        }
        if (!Q.isEmpty()){
            List<Vertex> findPath = new ArrayList<>();
            findPath.add(goal);
            Vertex vertex = vertexes.get(goal.getParentId());
            while(vertex.getParentId() != 0){
                findPath.add(vertex);
                vertex = vertexes.get(vertex.getParentId());
            }
            findPath.add(vertex);
            Collections.reverse(findPath);
            return findPath;
        }
        return null;
    }

    private double heuristic(Vertex o1, Vertex o2){
        return Math.abs(o1.getHDistance() - o2.getHDistance());
    }


    /**
     * Получение дистанции между вершинами
     * @param o1 вершина 1
     * @param o2 вершина 2
     * @return вистанция между вершинами в метрах
     * TODO вернуть private
     */
    public double distance(Vertex o1, Vertex o2){
        for (Edge ed : edges){
            if((ed.getInputVertexId() == o1.getId() | ed.getOutputVertexId() == o1.getId())
                    &(ed.getInputVertexId() == o2.getId() | ed.getOutputVertexId() == o2.getId())){
                return ed.getDistance();
            }
        }
        return -1;
    }
}
