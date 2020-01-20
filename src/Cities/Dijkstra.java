package Cities;

import java.util.*;

public class Dijkstra {

    private final Graph graph;
    private final City goal;

    public Dijkstra(Graph graph, City goal) {
        this.graph = graph;
        this.goal = goal;
    }

    public void run() {
        List<City> list = new ArrayList<City>(graph.getNodes());
        while (!list.isEmpty()) {
            City node = getMin(list);
            if (node.equals(goal))
                break;
            for (City neighbor : graph.getChildren(node)) {
                int d = node.distance() + graph.getCost(node, neighbor);
                if (d < neighbor.distance()) {
                    neighbor.setDistance(d);
                    neighbor.setPrevious(node);
                }
            }
        }
    }

    public City getMin(List<City> list) {
        City min = list.get(0);
        for (int i=0 ; i < list.size() ; i++) {
            if (list.get(i).distance() < min.distance())
                min = list.get(i);
        }
        list.remove(min);
        return min;
    }
}