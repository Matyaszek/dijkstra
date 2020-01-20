package Cities;

import java.util.*;

public class Graph {

    private Map<City, HashMap<City, Integer>> graph;

    public Graph() {
        graph = new HashMap<City, HashMap<City, Integer>>();
    }

    public void addNode(City node) {
        HashMap<City, Integer> map = new HashMap<City, Integer>();
        graph.put(node, map);
    }

    public void addEdge(City n1, City n2, int cost) {
        graph.get(n1).put(n2, cost);
    }

    public List<City> getNodes() {
        List<City> nodes = new ArrayList<City>(graph.keySet());
        return nodes;
    }

    public List<City> getChildren(City node) {
        List<City> children = new ArrayList<City>(graph.get(node).keySet());
        return children;
    }

    public Integer getCost(City n1, City n2) {
        return graph.get(n1).get(n2);
    }


}