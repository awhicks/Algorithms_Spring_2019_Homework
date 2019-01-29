import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AppleSortTest {

    @Test(timeout = 1000)
    public void testBaskets() {
        Apple[] basket0 = {new Apple(1,1), new Apple(2,1)};
        Apple[] basket1 = {new Apple(1,10), new Apple(2,10)};
        ArrayList<Apple[]> list = new ArrayList();
        list.add(basket0);
        list.add(basket1);
        Comparable[] apples =  AppleSort.sort(list);
        assertEquals(((Apple)apples[0]).type , 1);
        assertEquals(((Apple)apples[0]).deliciousness , 1);
        assertEquals(((Apple)apples[1]).type , 2);
        assertEquals(((Apple)apples[1]).deliciousness , 1);
        assertEquals(((Apple)apples[2]).type , 1);
        assertEquals(((Apple)apples[2]).deliciousness , 10);
        assertEquals(((Apple)apples[3]).type , 2);
        assertEquals(((Apple)apples[3]).deliciousness , 10);
    }



    @Test(timeout = 1000)
    public void testUnevenBasketsI() {
        Apple[] basket0 = {new Apple(1,1), new Apple(2,1),
                new Apple(2,1)};
        Apple[] basket1 = {new Apple(1,10), new Apple(2,10)};
        ArrayList<Apple[]> list = new ArrayList();
        list.add(basket0);
        list.add(basket1);
        Comparable[] apples =  AppleSort.sort(list);
        assertEquals(((Apple)apples[0]).type , 1);
        assertEquals(((Apple)apples[0]).deliciousness , 1);
        assertEquals(((Apple)apples[1]).type , 2);
        assertEquals(((Apple)apples[1]).deliciousness , 1);
        assertEquals(((Apple)apples[2]).type , 2);
        assertEquals(((Apple)apples[2]).deliciousness , 1);
        assertEquals(((Apple)apples[3]).type , 1);
        assertEquals(((Apple)apples[3]).deliciousness , 10);
        assertEquals(((Apple)apples[4]).type , 2);
        assertEquals(((Apple)apples[4]).deliciousness , 10);

    }


    @Test(timeout = 1000)
    public void testUnevenBasketsII() {
        Apple[] basket0 = {new Apple(1,1), new Apple(2,1)};
        Apple[] basket1 = {new Apple(1,10), new Apple(2,10),new Apple(1,10)};
        ArrayList<Apple[]> list = new ArrayList();
        list.add(basket0);
        list.add(basket1);
        Comparable[] apples =  AppleSort.sort(list);
        assertEquals(((Apple)apples[0]).type , 1);
        assertEquals(((Apple)apples[0]).deliciousness , 1);
        assertEquals(((Apple)apples[1]).type , 2);
        assertEquals(((Apple)apples[1]).deliciousness , 1);
        assertEquals(((Apple)apples[2]).type , 1);
        assertEquals(((Apple)apples[2]).deliciousness , 10);
        assertEquals(((Apple)apples[3]).type , 2);
        assertEquals(((Apple)apples[3]).deliciousness , 10);
        assertEquals(((Apple)apples[4]).type , 1);
        assertEquals(((Apple)apples[4]).deliciousness , 10);

    }




}

