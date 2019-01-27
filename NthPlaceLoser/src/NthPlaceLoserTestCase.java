import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;

public class NthPlaceLoserTestCase {


    /**
     *
     * @param n number of the student to create
     * @return
     */
    public static Student[] RandomStudentArray (int n) {
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student(i-1 ,Integer.toString(i));
        }
        shuffle(students);
        return students;
    }


    public static void shuffle(Student[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = ThreadLocalRandom.current().nextInt(0, i + 1);
            Student swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    /**
     * Test several k select methods
     */
    @Test(timeout = 750)
    public void testKSelectRandom (){
        int N = 100;
        Student[] students = RandomStudentArray(N);
        for( int i =0; i < 100000; i++){
            shuffle(students);
            int randomPlace = ThreadLocalRandom.current().nextInt(0, 10 + 1);
            assertEquals(NthPlaceLoser.NthPlaceLoser(students, randomPlace ).rank,  randomPlace-1);
        }
    }

}