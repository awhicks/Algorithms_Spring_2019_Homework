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



    @Test(timeout = 2000)
    public void testScale(){
        /**
         * 2x million apples.
         */
        Random rnd = ThreadLocalRandom.current();
        int maxBasket = 20;
        int maxApples = 100000;
        ArrayList<Apple[]> allThemApples = new ArrayList<>();
        for(int i = 0; i < maxBasket; i++){
            int randomNumberOfApples = ((ThreadLocalRandom) rnd).nextInt(maxApples);
            Apple[] basket = new Apple[randomNumberOfApples];
            int previousApple = 0;
            for(int j =0; j < randomNumberOfApples; j++){
                int next = Math.min(previousApple + ((ThreadLocalRandom) rnd).nextInt(2),10);
                basket[j] = new Apple(j%2 , next);
                previousApple = next;
            }
            allThemApples.add(basket);
        }
        Comparable[] apples = AppleSort.sort(allThemApples);
        assert(isSorted(apples));

    }

    /**
     * Demonstrate that merge sort will not work.
     */

    public void sortAmillion(){
        int M = 2000000;
        Comparable[] numbers = new Comparable[M];
        for(int i =0; i < M; i++){
            numbers[i] = new Apple(i%2, (int)(Math.random()*M));
        }
        Arrays.sort(numbers);
    }

    /**
     *
     * @return
     */
    @Test(timeout = 1000)
    public void testStability(){
        Random rnd = ThreadLocalRandom.current();
        int maxBasket = 20;
        int maxApples = 100; //Needs to end on a even because of the stability test.
        ArrayList<Apple[]> allThemApples = new ArrayList<>();
        for(int i = 0; i < maxBasket; i++){
            Apple[] basket = new Apple[maxApples];
            for(int j =0; j < maxApples; j++){
                basket[j] = new Apple(j%2 , 10);
            }
            allThemApples.add(basket);
        }
        Comparable[] apples = AppleSort.sort(allThemApples);
        assert(isStable(apples));
    }

    private boolean isStable(Comparable[] apples) {
        int numApples = apples.length;
        int previousType = -1;
        for (int i = 0; i < numApples; i++) {
            Apple a = (Apple) apples[i];
            if ( a.type == previousType ) {
                return false;
            } else {
                previousType = a.type;
            }
        }
        return true;
    }


    public boolean isSorted(Comparable[] apples) {
        int numApples = apples.length;
        int previousType = -1;
        int previousNumber = -1;
        for (int i = 0; i < numApples; i++) {
            Apple a = (Apple) apples[i];
            if (!(a.deliciousness >= previousNumber)) {
                return false;
            } else {
                previousNumber = a.deliciousness;
            }
        }
        return true;
    }
}


