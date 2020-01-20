package Cities;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
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
            if(s==null) System.out.println("PODAJ POPRAWNE MIASTO!");
        }
        result = false;
        ArrayList<String> targets = new ArrayList<>();
        ArrayList<Integer> packs = new ArrayList<Integer>();
        Scanner scanner2 = new Scanner(System.in);
        boolean next = true;
        while(next){
            System.out.println("Podaj miasto docelowe:");
            k = scanner.nextLine();
            //System.out.println("ilość paczek do dostarczenia w "+k+": ");
            //packs.add(scanner.nextInt());
            result = cities.contains(k);
            targets.add(k);
            System.out.println("Koniec?: ");
            if(scanner2.nextLine().equals("y")) next=false;
        }
        //tworzy permutacje trasy
        List<List<String>> alternatives = generatePerm(targets);

        for(int i=0;i<alternatives.size();i++){
            //dodaje miasto początkowe do wszystkich alternaywnych tras
            alternatives.get(i).add(0,s);
        }
        System.out.println(alternatives.toString());

        int fullDistance = 0;
        String prevCity = s;
        System.out.println("Przystanki: ");
        for (List<String> list:alternatives
             ) {
            prevCity = s;
            for (String target:list
            ) {
                fullDistance+=distance(prevCity,target);
                prevCity = target;

            }
            //dodanie do dystansu powrót do głównego miasta
            fullDistance+=distance(prevCity,s);

            System.out.println("Cały dystans: "+fullDistance);
            System.out.println(list);
            fullDistance = 0;
        }

    }
    //generuje permutacje tras bez uwzględnienia miasta głównego
    public static <E> List<List<E>> generatePerm(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
    //liczy dystans z miasta start do end (szuka połączenia)
    private static int distance(String start, String end){

        MainCities mc = new MainCities();

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
        //System.out.println("Najkrotsza droga: " + path);
        int dis = mc.getCity(end).distance();
        //System.out.println("Dystans (w kilometrach): " + dis);
        return dis;
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
