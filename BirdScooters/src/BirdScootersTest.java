import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import static org.junit.Assert.*;

public class BirdScootersTest {
    private int numberOfQueryies = 1000000;
    private int  numberOfLocations = 10000;


    @Test(timeout = 20)
    public void testingSmallCase(){
        BirdScooters bsScooters = new BirdScooters();
        bsScooters.add(new Node( 0.5, 0.5));
        bsScooters.add(new Node( 0.25, 0.25));
        bsScooters.add(new Node( 0.125, 0.125));
        bsScooters.add(new Node( 0.375, 0.375));
        bsScooters.add(new Node( 0.75, 0.75));
        bsScooters.add(new Node( 0.55, 0.55));
        bsScooters.add(new Node( 0.90, 0.90));
        Node closestPoint = bsScooters.closestPoint(new Node(0.1, 0.1 ));
        assertEquals(0.125, closestPoint.x, .000001);
        closestPoint = bsScooters.closestPoint(new Node(0.255, 0.255 ));
        assertEquals(0.25, closestPoint.x, .0000001);
        closestPoint = bsScooters.closestPoint(new Node(0.99, 0.99 ));
        assertEquals(0.9, closestPoint.x, 0.0000001);
    }

    @Test(timeout = 20)
    public void testingSmallCase2() {
        BirdScooters bsScooters = new BirdScooters();
        bsScooters.add(new Node(0.1, 0.5));
        bsScooters.add(new Node(0.225, 0.25));
        bsScooters.add(new Node(0.525, 0.125));
        bsScooters.add(new Node(0.775, 0.375));
        bsScooters.add(new Node(0.75, 0.25));
        bsScooters.add(new Node(0.55, 0.75));
        bsScooters.add(new Node(0.95, 0.10));
        Node closestPoint = bsScooters.closestPoint(new Node(0.99, 0.1));
        assertEquals(new Node(.95, .1), closestPoint);
        closestPoint = bsScooters.closestPoint(new Node(0.526, 0.126));
        assertEquals(new Node(.525, .125), closestPoint);
        closestPoint = bsScooters.closestPoint(new Node(0.555, 0.755));
        assertEquals(new Node(.55, .75), closestPoint);
    }

    @Test
    public void testingSmallLatice() {
        BirdScooters bsScooters = new BirdScooters();
        bsScooters.add(new Node(0.0, 0.0));
        bsScooters.add(new Node(0.0, 1.0));;
        bsScooters.add(new Node(1.0, 0.0));
        bsScooters.add(new Node(1.0, 1.0));
        assertEquals(new Node(1, 1), bsScooters.closestPoint(new Node(0.85, 0.97)));
        assertEquals(new Node(1, 1), bsScooters.closestPoint(new Node(0.9, 1.0)));
    }

    /**
     * Outlier test
     * with clusters
     */
    @Test(timeout = 30)
    public void testOutliner(){
        BirdScooters bsScooters = new BirdScooters();
        bsScooters.add(new Node(0.8, 0.8));
        for (int i = 0; i < 10000; i++) {
            double x = Math.random()/5;
            double y = Math.random()/5;
            bsScooters.add(new Node(x,y));
        }
        Node node = bsScooters.closestPoint(new Node(0.7, 0.7));
        assertEquals(0.8, node.y, 0.000001);
    }

    @Test(timeout = 1000)
    public void testVertical() {
        int num = 10000;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        ArrayList<Double> ys = new ArrayList<>(num);
        for (int i = 0; i < num; i++)
            ys.add(rnd.nextDouble((double)num));
        BirdScooters bs = new BirdScooters();
        for (double y : ys) {
            bs.add(new Node(0, y));
        }
        Collections.sort(ys);
        int queries = 1000;
        for (int i = 0; i < queries; i++) {
            double y = rnd.nextDouble((double)num);
            int index = Collections.binarySearch(ys, y);
            double d1 = Double.MAX_VALUE, d2 = Double.MAX_VALUE;
            if (index >= 0)
                d1 = ys.get(index);
            else {
                int insertionPoint = -1*(index+1);
                if (insertionPoint < num)
                    d1 = ys.get(insertionPoint);
                if (insertionPoint > 0)
                    d2 = ys.get(insertionPoint-1);
            }
            Node expected;
            Node input = new Node(rnd.nextDouble((double)num), y);
            Node n1 = new Node(0, d1);
            Node n2 = new Node(0, d2);
            if (Distance(input, n1) < Distance(input, n2))
                expected = n1;
            else
                expected = n2;
            Node actual = bs.closestPoint(input);
            assertEquals(expected, actual);
        }
    }

    @Test(timeout = 500)
    public void testHorizontal() {
        int num = 10000;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        ArrayList<Double> xs = new ArrayList<>(num);
        for (int i = 0; i < num; i++)
            xs.add(rnd.nextDouble((double)num));
        BirdScooters bs = new BirdScooters();
        for (double x : xs)
            bs.add(new Node(x, 0));
        Collections.sort(xs);
        int queries = 1000;
        for (int i = 0; i < queries; i++) {
            double x = rnd.nextDouble((double)num);
            int index = Collections.binarySearch(xs, x);
            double d1 = Double.MAX_VALUE, d2 = Double.MAX_VALUE;
            if (index >= 0)
                d1 = xs.get(index);
            else {
                int insertionPoint = -1*(index+1);
                if (insertionPoint < num)
                    d1 = xs.get(insertionPoint);
                if (insertionPoint > 0)
                    d2 = xs.get(insertionPoint-1);
            }
            Node expected;
            Node input = new Node(x, rnd.nextDouble((double)num));
            Node n1 = new Node(d1, 0);
            Node n2 = new Node(d2, 0);
            if (Distance(input, n1) < Distance(input, n2))
                expected = n1;
            else
                expected = n2;
            Node actual = bs.closestPoint(input);
            assertEquals(expected, actual);
        }
    }

    @Test(timeout = 1000)
    public void testDiagonal() {
        int num = 10000;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        ArrayList<Double> xs = new ArrayList<>(num);
        for (int i = 0; i < num; i++)
            xs.add(rnd.nextDouble((double)num) - num/2);
        BirdScooters bs = new BirdScooters();
        for (double x : xs)
            bs.add(new Node(x, -1*x));
        Collections.sort(xs);
        int queries = 1000;
        for (int i = 0; i < queries; i++) {
            double x = rnd.nextDouble((double)num) - num/2;
            int index = Collections.binarySearch(xs, x);
            double d1 = Double.MAX_VALUE, d2 = Double.MAX_VALUE;
            if (index >= 0)
                d1 = xs.get(index);
            else {
                int insertionPoint = -1*(index+1);
                if (insertionPoint < num)
                    d1 = xs.get(insertionPoint);
                if (insertionPoint > 0)
                    d2 = xs.get(insertionPoint-1);
            }
            Node expected;
            double offset = rnd.nextDouble(4.0*num) - 2*num;
            Node input = new Node(x + offset, -1*x + offset);
            System.out.println(input);
            Node n1 = new Node(d1, -1*d1);
            Node n2 = new Node(d2, -1*d2);
            if (Distance(input, n1) < Distance(input, n2))
                expected = n1;
            else
                expected = n2;
            Node actual = bs.closestPoint(input);
            assertEquals(expected, actual);
        }
    }

    @Test(timeout = 1000)
    public void testLattice() {
        double num = 100;
        double delta = 1.25;
        BirdScooters bs = new BirdScooters();
        for (double i = -1*num; i <= num; i += delta) {
            for (double j = -1*num; j <= num; j += delta) {
                Node n = new Node(i, j);
                bs.add(n);
            }
        }
        int queries = 1000;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = 0; i < queries; i++) {
            double x = rnd.nextDouble(num*2) - num;
            double y = rnd.nextDouble(num*2) - num;
            Node input = new Node(x, y);
            double expectedX = Math.round(x/delta)*delta;
            double expectedY = Math.round(y/delta)*delta;
            Node expected = new Node(expectedX, expectedY);
            //System.out.println(input +", "+ expected);
            assertEquals(expected, bs.closestPoint(input));
        }
    }

    @Test(timeout = 10000)
    public void testRandom() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int num = rnd.nextInt(1000) + 100;
        int queries = rnd.nextInt(100) + 1;
        double bound = rnd.nextDouble(10000000)+10;
        int repeats = rnd.nextInt(50) + 10;
        List<Node> nodes = new ArrayList<>();
        BirdScooters bs = new BirdScooters();
        for (int repeat = 0; repeat < repeats; repeat++) {
            for (int i = 0; i < num; i++) {
                Node n = new Node(rnd.nextDouble(bound) - bound / 2, rnd.nextDouble(bound) - bound / 2);
                nodes.add(n);
                bs.add(n);
            }
            for (int i = 0; i < queries; i++) {
                Node n = new Node(rnd.nextDouble(bound) - bound / 2, rnd.nextDouble(bound) - bound / 2);
                assertEquals(bruteForce(nodes, n), bs.closestPoint(n));
            }
        }
    }

    private Node bruteForce(List<Node> nodes, Node query) {
        double diff = Double.MAX_VALUE;
        Node selection = null;
        for (Node n : nodes) {
            double d = Distance(query, n);
            if (d < diff) {
                selection = n;
                diff = d;
            }
        }
        return selection;
    }

    public double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

}
