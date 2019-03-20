public class Road  {
    private final String id;
    private final City source;
    private final City destination;
    private float tollCost;

    public Road(String id, City source, City destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }
    public City getDestination() {
        return destination;
    }

    public City getSource() {
        return source;
    }
    public float getTollCost() {
        return tollCost;
    }

    public void setTollCost(int cost) {
        this.tollCost = cost;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}