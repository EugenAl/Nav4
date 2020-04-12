package dpr.svich.nav4.pathfinder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import lombok.Data;

/**
 * Класс описывающий вершину графа
 */
@Data
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

    //  дистанция от входа (эвристическая)
    private double xDistance;

    //  высота от входа (эвристическая)
    private double yDistance;

    //  родительская вершина
    private int parentId;

    @Override
    public boolean equals(@Nullable Object obj) {
        Vertex v = (Vertex) obj;
        return (v != null && v.id == this.id && v.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Vertex(String name, int id, double xDistance, double yDistance) {
        this.name = name;
        this.id = id;
        this.xDistance = xDistance;
        this.yDistance = yDistance;
        adjacentVertex = new ArrayList<>();
    }

    public String toString(){
        return this.getId() + " : " + this.name;
    }
}
