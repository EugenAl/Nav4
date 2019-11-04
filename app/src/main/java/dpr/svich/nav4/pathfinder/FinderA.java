package dpr.svich.nav4.pathfinder;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс для поиска пути по алгоритму А*
 */
public class FinderA {
    public class EdgesPair {
        private long id;
        private int outputVertexId;
        private int inputVertexId;
        private double distance;

        public EdgesPair(long id, int outputVertexId, int inputVertexId, double distance) {
            this.id = id;
            this.outputVertexId = outputVertexId;
            this.inputVertexId = inputVertexId;
            this.distance = distance;
        }
    }

    SparseArray<Vertex> vertexes = new SparseArray<>();
    List<EdgesPair> edges = new ArrayList<>();

    private void init() {
        edges.add(new EdgesPair(0, 1, 2, 2d));
        edges.add(new EdgesPair(1, 1, 3, 3.2d));
        edges.add(new EdgesPair(2, 3, 4, 2.5d));
        edges.add(new EdgesPair(3, 2, 5, 8d));
        edges.add(new EdgesPair(4, 5, 6, 4d));
        edges.add(new EdgesPair(5, 6, 7, 2.5d));
        edges.add(new EdgesPair(6, 4, 8, 3.5d));
        edges.add(new EdgesPair(7, 8, 9, 8d));
        edges.add(new EdgesPair(8, 7, 9, 3.2d));

        vertexes.put(1, new Vertex("Вход", 1, 0));
        vertexes.put(2, new Vertex("Лестница", 2, 2d));
        vertexes.put(3, new Vertex("к.1", 3, 3.2d));
        vertexes.put(4, new Vertex("к.2", 4, 5.7d));
        vertexes.put(5, new Vertex("Лестница", 5, 10d));
        vertexes.put(6, new Vertex("к.8", 6, 14d));
        vertexes.put(7, new Vertex("к.9", 7, 16.5d));
        vertexes.put(8, new Vertex("Лестница", 8, 9.2d));
        vertexes.put(9, new Vertex("Лестница", 9, 17.2d));
    }

    public FinderA() {
        init();

        for (EdgesPair edgesPair : edges) {
            vertexes.get(edgesPair.outputVertexId).getAdjacentVertex()
                    .add(vertexes.get(edgesPair.inputVertexId));
            vertexes.get(edgesPair.inputVertexId).getAdjacentVertex()
                    .add(vertexes.get(edgesPair.outputVertexId));
        }
    }

    /**
     * Функция поиска пути по алгоритму А*
     * Возвращает null если путь не найден.
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
        return null;
    }

    private double heuristic(Vertex o1, Vertex o2){
        return Math.abs(o1.getHDistance() - o2.getHDistance());
    }

    private double distance(Vertex o1, Vertex o2){
        for (EdgesPair ed : edges){
            if((ed.inputVertexId == o1.getId() | ed.outputVertexId == o1.getId())
                    &(ed.inputVertexId == o2.getId() | ed.outputVertexId == o2.getId())){
                return ed.distance;
            }
        }
        return -1;
    }
}
