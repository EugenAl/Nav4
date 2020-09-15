package dpr.svich.nav4.pathfinder;

import java.util.List;

import androidx.collection.SparseArrayCompat;

public class Finder {

    protected SparseArrayCompat<Vertex> vertexes;
    protected List<Edge> edges;

    public Finder() {
        // Подключение графа к системе поиска пути
        Graph graph = new Graph();
        vertexes = graph.getVertexes();
        edges = graph.getEdges();
    }

    public List<Vertex> findPath(int startId, int goalId){
        return null;
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
