package dpr.svich.nav4.pathfinder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Вершина графа
 */
public class Vertex {

    //  значение эвристической функции
    private double heuristicFunction;

    //  имя вершины
    private String name;

    //  идентификатор вершины
    private int id;

    //  стоимость пути
    private double g;

    //  список смежных вершин
    private List<Vertex> adjacentVertex;

    @Override
    public boolean equals(@Nullable Object obj) {
        Vertex v = (Vertex) obj;
        return (v != null && v.id == this.id && v.name.equals(this.name));
    }

    public Vertex(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
