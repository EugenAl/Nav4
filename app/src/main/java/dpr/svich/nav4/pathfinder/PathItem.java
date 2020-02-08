package dpr.svich.nav4.pathfinder;

import lombok.Data;

/**
 * Класс для обертки данных пути в путевые точки.
 */
@Data
public class PathItem {

    // описание путевой точки
    private String description;
    // расстояние к путевой точке
    private double distance;
    // id сопровождающей картинки
    private int imageResId;

    public PathItem(String description, double distance, int imageResId) {
        this.description = description;
        this.distance = distance;
        this.imageResId = imageResId;
    }

    public PathItem(){}

    @Override
    public String toString() {
        return "PathItem{" +
                "description='" + description + '\'' +
                ", (" + distance + ")}";
    }
}
