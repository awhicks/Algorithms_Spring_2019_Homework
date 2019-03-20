import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoadTrip {

    private final List<City> cities;
    private final List<Road> roads;
    private int[][] stateTolls; // toll for x, y would be states[x][y]. Eg. toll for 01 is states[0][1]

    public RoadTrip(Graph graph, int[][] stateTolls) {
        this.cities = new ArrayList<City>(graph.getCities());
        this.roads = new ArrayList<Road>(graph.getRoads());
        this.stateTolls = stateTolls;
    }


    public void computeCostsFromSource(City source) {
        // TODO
    }


    public LinkedList<City> getPath(City target) {
        // TODO
        LinkedList<City> path = new LinkedList<City>();
        return path;
    }
}
