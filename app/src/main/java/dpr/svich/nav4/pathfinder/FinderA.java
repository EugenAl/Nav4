package dpr.svich.nav4.pathfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для поиска пути по алгоритму А*
 */
public class FinderA {
    public class EdgesPair{
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

    List<Vertex> vertexes = new ArrayList<>();
    List<EdgesPair> edges = new ArrayList<>();

    private void init(){
        edges.add(new EdgesPair(0, 1, 2, 2d));
        edges.add(new EdgesPair(1, 1, 3, 3.2d));
        edges.add(new EdgesPair(2, 3, 4, 2.5d));
        edges.add(new EdgesPair(3, 2, 5, 8d));
        edges.add(new EdgesPair(4, 5, 6, 4d));
        edges.add(new EdgesPair(5, 6, 7, 2.5d));
        edges.add(new EdgesPair(6, 4, 8, 3.5d));
        edges.add(new EdgesPair(7, 8, 9, 8d));
        edges.add(new EdgesPair(8, 7, 9, 3.2d));

        vertexes.add(new Vertex("Вход", 1));
        vertexes.add(new Vertex("Лестница", 2));
        vertexes.add(new Vertex("к.1", 3));
        vertexes.add(new Vertex("к.2", 4));
        vertexes.add(new Vertex("Лестница", 5));
        vertexes.add(new Vertex("к.8", 6));
        vertexes.add(new Vertex("к.9", 7));
        vertexes.add(new Vertex("Лестница", 8));
        vertexes.add(new Vertex("Лестница", 9));
    }

    public FinderA(){
        init();

        for(EdgesPair edgesPair : edges){

        }
    }
}
