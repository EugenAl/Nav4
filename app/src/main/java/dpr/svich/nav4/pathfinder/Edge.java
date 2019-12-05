package dpr.svich.nav4.pathfinder;

import lombok.Data;

/**
 * Класс описывающий ребро графа
 */
@Data
public class Edge {
    private long id;
    private int outputVertexId;
    private int inputVertexId;
    private double distance;

    public Edge(long id, int outputVertexId, int inputVertexId, double distance) {
        this.id = id;
        this.outputVertexId = outputVertexId;
        this.inputVertexId = inputVertexId;
        this.distance = distance;
    }
}
