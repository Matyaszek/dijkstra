package Cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MainCities {


    public static String FILE_PATH = "src/Cities/MainCities.txt"; // sciezka do pliku

    public HashMap<String, City> cities; //slownik przechowuje nazwe miasta i obiekt
    public Graph graph;

    public MainCities() {
        cities = new HashMap<String, City>();
        graph = new Graph();
        loadGraph();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setStart(String nazwaMiasta) {
        cities.get(nazwaMiasta).setDistance(0);
    }

    public City getCity(String nazwaMiasta) {
        return cities.get(nazwaMiasta);
    }

    private void loadGraph() {
        loadCities();
        loadEdges();
    }

    private void loadCities() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                String cName = line.substring(0, line.indexOf("-"));
                City c = new City(cName);
                cities.put(cName, c);
                graph.addNode(c);
            }
            br.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadEdges() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                String c1 = line.substring(0, line.indexOf("-"));
                String[] adjacent = line.substring(
                        line.indexOf("-") + 1).split(",");
                for (String s : adjacent) {
                    String c2 = s.substring(0, s.indexOf("("));
                    String cost = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
                    graph.addEdge(
                            cities.get(c1),
                            cities.get(c2),
                            Integer.parseInt(cost));
                }
            }
            br.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        MainCities mc = new MainCities();
        //System.out.println(mc.miasta.toString());

        Set cities = mc.cities.keySet();
        showCities(cities);

        Scanner scanner = new Scanner(System.in);


        boolean result = false;
        String s ="";
        String k ="";
        while(result==false){
            System.out.println("Podaj miasto poczatkowe:");
            s = scanner.nextLine();
            result = cities.contains(s);
            System.out.println("PODAJ POPRAWNE MIASTO!");
        }
        result = false;
        while(result==false){
            System.out.println("Podaj miasto docelowe:");
            k = scanner.nextLine();
            System.out.println("PODAJ POPRAWNE MIASTO!");
            result = cities.contains(k);
        }
        String start = s;
        String end = k;


        mc.setStart(start);
        Dijkstra d = new Dijkstra(mc.getGraph(), mc.getCity(end));
        d.run();

        List<City> path = new ArrayList<City>();
        City city = mc.getCity(end);

        while (city.previous() != null) {
            path.add(city);
            city = city.previous();
        }
        path.add(city);
        Collections.reverse(path);
        System.out.println("Najkrotsza droga: " + path);
        System.out.println("Dystans (w kilometrach): " + mc.getCity(end).distance());


    }
    private static void showCities(Set cities){
        int i = 0;
        for (Object city:cities
        ) {
            System.out.println((i+1)+". "+city.toString());
            i++;
        }
    }
}
