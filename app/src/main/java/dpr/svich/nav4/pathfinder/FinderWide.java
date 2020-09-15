package dpr.svich.nav4.pathfinder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FinderWide extends Finder {
    @Override
    public List<Vertex> findPath(int startId, int goalId) {
        if(startId == goalId){
            return null;
        }
        Vertex start = vertexes.get(startId);
        Vertex goal = vertexes.get(goalId);
        // множество вершин пути
        List<Vertex> path = new ArrayList<>();
        // список просмотренных вершин
        List<Vertex> visited = new ArrayList<>();
        // множество вершин, которые нужно просмотреть
        Queue<Vertex> Q = new ArrayDeque<>();
        Q.add(start);
        path.add(start);
        visited.add(start);
        while(!Q.isEmpty()){
            Vertex current = Q.remove();
            // путь найден
            if(current == goal){
                path.add(current);
                return path;
            }
            visited.add(current);
            for (Vertex v: current.getAdjacentVertex()) {
                if(!visited.contains(v)){
                    Q.add(v);
                    v.setParentId(current.getId());
                    path.add(v);
                }
            }
        }
        // путь не найден
        return null;
    }
}
