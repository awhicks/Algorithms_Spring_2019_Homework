import java.util.Arrays;

public class Point {

    public double[] cordinates;


    /**
     * Constructor
     * @param cordinates vector of cordinates example [x, y, z]
     */
    public Point(double[] cordinates){
        this.cordinates = cordinates;
    }


    /**
     * Returns a string respresentation fo the points
     * @return
     */
    public String toString() {
        return Arrays.toString(this.cordinates);

    }


    /**
     * Return the euclidean distance to a point.
     * @param point
     * @return
     */
    public double distance(Point point) {
        //TODO: Implement this method
        double distance = 0;
        for(int i = 0; i < this.cordinates.length; i++){
            distance += Math.pow((this.cordinates[i] - point.cordinates[i]),2);
        }
        return Math.sqrt(distance);


    }
}
