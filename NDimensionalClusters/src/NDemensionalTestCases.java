import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertTrue;

public class NDemensionalTestCases {

    /**
     * Test four point lattices of different sizes.
     */
    @Test
    public void testSmallLatices(){
        int minSize = 1;
        int maxSize = 1;
        for(int i = minSize; i <= maxSize; i++){
            NDemensionalClusters ndc = new NDemensionalClusters();
            ArrayList<Point> lattice = new ArrayList<Point>();
            Point p1 = new Point(new double[]{0, 0});
            ndc.add(p1);
            Point p2 = new Point(new double[]{i, 0});
            ndc.add(p2);
            Point p3 = new Point(new double[]{0, i});
            ndc.add(p3);
            Point p4 = new Point(new double[]{i, i});
            ndc.add(p4);

            double sum = p1.distance(p4) + p2.distance(p3) + p1.distance(p2);
            assertTrue(Math.round(ndc.getSum(1)) == Math.round(sum));

            double sum2 = p1.distance(p4) + p2.distance(p3);
            assertTrue(Math.round(ndc.getSum(2)) == Math.round(sum2));

            double sum3 = p1.distance(p4);
            assertTrue(Math.round(ndc.getSum(3)) == Math.round(sum3));

            double sum4 = 0;
            assertTrue(Math.round(ndc.getSum(4)) == Math.round(sum4));
        }
    }

    /**
     *
     * Circle centered a zero
     * (x - a)^2 + (y -b)^2 = r^2
     */
    @Test
    public void testCircle(){
        double radius = 1;
        double delta = 0.1;
        NDemensionalClusters ndc = new NDemensionalClusters();

        int count = 0;
        double a = 0;
        double b = 0;
        //Adds a point at the center of the circle
        ndc.add(new Point(new double[]{a, b}));
        for(double i = 0; i <= radius/2; i+= delta){
            double ySquared = radius * radius - (i - a) * (i - a);
            double y = Math.sqrt(ySquared) - b;
            ndc.add(new Point(new double[]{i, y}));
            count++;
        }
        assertTrue(ndc.getSum(1)  == count*radius);
    }

    /**
     * This test case generates a sphere of points.
     * With a point in the middle
     * Each point in the sphere.
     *  (x - a)² + (y - b)² + (z - c)² = r²,
     */
    @Test
    public void testSphere() {
        double radius = 1;
        double delta = 0.1;
        NDemensionalClusters ndc = new NDemensionalClusters();
        double a = 0;
        double b = 0;
        double c = 0;
        ndc.add(new Point(new double[]{a, b, c}));
        int count = 0;
        for (double x = 0; x <= radius/2; x += delta) {
            for (double y = 0; y <= radius/2; y += delta) {
                double zsquared = radius * radius - (x - a) * (x -a) - (y - b) * (y -b);
                double z = Math.sqrt(zsquared) - c;
                ndc.add(new Point(new double[]{x, y, z}));
                count++;
            }
        }
        assertTrue(ndc.getSum(1)  == count*radius);
    }

    /**
     * Test Beyond three demensions
     * Wheel moving through time
     */
    @Test
    public void testBeyond3Demensions(){

        double radius = 1;
        double delta = 0.1;
        NDemensionalClusters ndc = new NDemensionalClusters();

        double spaceTime = 0.03;
        int count = 0;
        for(double space =0; space < spaceTime; space+= 0.01){

            double a = 0;
            double b = 0;
            //Adds a point at the center of the circle
            ndc.add(new Point(new double[]{a, b, 0, (double)space}));
            for(double i = 0; i <= radius/2; i+= delta){
                double ySquared = radius * radius - (i - a) * (i - a);
                double y = Math.sqrt(ySquared) - b;
                ndc.add(new Point(new double[]{i, y, 0, (double)space}));
                count++;
            }
        }
        assertTrue(ndc.getSum(3)  >= count*radius);
    }
}
