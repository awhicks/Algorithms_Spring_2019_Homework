import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class twitterPublicTestCase {

    public void testBasicFindKPeople() {
        twitterMap example = new twitterMap(7);

        example.addFollower(0, 1);
        example.addFollower(0, 2);
        example.addFollower(1, 3);
        example.addFollower(4, 1);
        example.addFollower(6, 4);
        example.addFollower(4, 6);

        example.addFollower(6, 0);
        example.addFollower(5, 2);
        example.addFollower(5, 6);
        example.addFollower(6, 5);

        List<Integer> ans = example.findKPeople();

        List<Integer> ansReal = new ArrayList<Integer>();
        ansReal.add(4);
        ansReal.add(5);
        ansReal.add(6);

        assertEquals(ansReal, ans);
    }



    @Test(timeout=10)
    public void callTests() {
        testBasicFindKPeople();

    }

}