package Cities;

public class City {
    private final String name;
    private int distance;
    private City previous;

    public City(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }

    public String name() {
        return name;
    }

    public int distance() {
        return distance;
    }

    public City previous() {
        return previous;
    }

    public void setDistance(int d) {
        distance = d;
    }

    public void setPrevious(City c) {
        previous = c;
    }

    public boolean equals(Object o) {
        if (o instanceof City) {
            City other = (City) o;
            return this.name.equals(other.name);
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return name.toString();
    }
}