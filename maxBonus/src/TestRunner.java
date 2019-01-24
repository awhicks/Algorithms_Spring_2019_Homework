import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRunner {

    // ----- Testing Constants ----- //

    private static final int PICK_CHECK_CONSISTENCY_UP_TO = 200;
    private static final int[] PICK_CONSISTENCY_LARGE = {1000, 2000, 4097, 10007};


    // ----- Helper Functions ----- //

    private static List<Integer> getRanks(int n) {
        ArrayList out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            out.add(i+1);
        }
        Collections.shuffle(out);
        return Collections.unmodifiableList(out);
    }


    // ----- NumWays Tests ----- //

    @Test(timeout = 10000)
    public void testNumWaysCorrectness() {
        int[] answers = new int[] {-1, 2, -1, -1, 2, -1, -1, 8, -1, -1, 40, -1, -1, 338, -1, -1, 526,
                -1, -1, 5448, -1, -1, 36168, -1, -1, 417920, };
        for (int n = 1; n < answers.length; n+=3) {
            List <Integer> ranks = getRanks(n);
            assertEquals("Incorrect numWays for " + ranks, answers[n], Draft.numWays(ranks));
        }
    }


    // ----- Pick Tests ----- //

    @Test(timeout = 10000)
    public void testPickConsistency() {
        for (int n = 1; n <= PICK_CHECK_CONSISTENCY_UP_TO; n++) {
            int teamA = 0, teamB = 0;
            int numA = 0, numB = 0;
            List<Integer> ranks = getRanks(n);
            for (int i = 0; i < ranks.size(); i++)
                if (!Draft.pick(ranks, i)) {
                    teamA += ranks.get(i);
                    numA ++;
                }
                else {
                    teamB += ranks.get(i);
                    numB ++;
                }
            assertTrue(String.format("Uneven teams for %s.", ranks.toString()), Math.abs(numA - numB) <= 1);
            int best = ((ranks.size() - 1) % 4 < 2) ? 1 : 0;
            assertEquals(String.format("Unfair teams for %s.", ranks.toString()), best, Math.abs(teamA - teamB));
        }
    }

    @Test(timeout = 10000)
    public void testPickConsistencyLargeShuffle() {
        for (int n : PICK_CONSISTENCY_LARGE) {
            int teamA = 0, teamB = 0;
            int numA = 0, numB = 0;
            List<Integer> ranks = getRanks(n);
            for (int i = 0; i < ranks.size(); i++)
                if (!Draft.pick(ranks, i)) {
                    teamA += ranks.get(i);
                    numA ++;
                }
                else {
                    teamB += ranks.get(i);
                    numB ++;
                }
            assertTrue(String.format("Uneven teams for %s.", ranks.toString()), Math.abs(numA - numB) <= 1);
            int best = ((ranks.size() - 1) % 4 < 2) ? 1 : 0;
            assertEquals(String.format("Unfair teams for %s.", ranks.toString()), best, Math.abs(teamA - teamB));
        }
    }

    @Test(timeout = 10000)
    public void testPickConsistencyLargeRange() {
        for (int n : PICK_CONSISTENCY_LARGE) {
            List<Integer> ranks = new TestingList(n);
            int teamA = 0, teamB = 0;
            int numA = 0, numB = 0;
            for (int i = 0; i < ranks.size(); i++)
                if (!Draft.pick(ranks, i)) {
                    teamA += ranks.get(i);
                    numA ++;
                }
                else {
                    teamB += ranks.get(i);
                    numB ++;
                }
            assertTrue(String.format("Uneven teams for %s.", ranks.toString()), Math.abs(numA - numB) <= 1);
            int best = ((ranks.size() - 1) % 4 < 2) ? 1 : 0;
            assertEquals(String.format("Unfair teams for %s.", ranks.toString()), best, Math.abs(teamA - teamB));
        }
    }



    // ----- Helper Classes ----- //

    /**
     * This is similar to the range class in Python, which is similar to a list, but does not actually store any values
     * allowing it to be constant space while representing arbitrarily large lists.
     */
    private static final class TestingList implements List<Integer> {

        private final int n;

        public TestingList(int n) {
            this.n = n;
        }

        @Override
        public int size() {
            return n;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Integer))
                return false;
            int i = (int) o;
            return i > 0 && i <= n;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return i < n;
                }

                @Override
                public Integer next() {
                    return ++i;
                }
            };
        }


        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object i : c)
                if (!contains(c))
                    return false;
            return true;
        }



        @Override
        public Integer get(int index) {
            if (!contains(index + 1))
                throw new NoSuchElementException("" + index);
            return index + 1;
        }


        @Override
        public int indexOf(Object o) {
            if (!contains(o))
                return -1;
            int i = (int) o;
            return i - 1;
        }

        @Override
        public int lastIndexOf(Object o) {
            return indexOf(o);
        }

        @Override
        public String toString() {
            if (n <= 10) {
                String out = "[1";
                for (int i = 2; i <= n; i++)
                    out += ", " + i;
                return out + "]";
            }
            return String.format("[1, 2, 3, ..., %d, %d, %d]", n-2, n-1, n);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TestingList))
                return false;
            return n == ((TestingList)o).n;
        }


        @Override
        public ListIterator<Integer> listIterator() {
            throw new UnsupportedOperationException("listIterator() not implemented for TestingList because TestingList is immutable.");
        }

        @Override
        public ListIterator<Integer> listIterator(int index) {
            throw new UnsupportedOperationException("listIterator() not implemented for TestingList because TestingList is immutable.");
        }

        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("subList() not implemented for TestingList");
        }

        @Override
        public Object[] toArray() {
            System.err.println("Warning: Calling toArray on TestingList may require A LOT of memory.");
            Object[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = get(i);
            return a;
        }

        @Override
        public <T> T[] toArray(T[] a) {

            System.err.println("Warning: Calling toArray on TestingList may require A LOT of memory.");
            if (a == null)
                throw new NullPointerException();
            if (!(a instanceof Integer[]))
                throw new ArrayStoreException("Type <T> is not Integer.");
            if (a.length < n)
                a = (T[])(new Object[n]);
            for (int i = 0; i < n; i++)
                a[i] = (T)get(i);
            return a;
        }

        @Override
        public boolean add(Integer integer) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot add().");
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot remove().");
        }

        @Override
        public Integer set(int index, Integer element) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot set().");
        }

        @Override
        public void add(int index, Integer element) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot add().");
        }

        @Override
        public Integer remove(int index) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot remove().");
        }
        @Override
        public boolean addAll(Collection<? extends Integer> c) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot add().");
        }

        @Override
        public boolean addAll(int index, Collection<? extends Integer> c) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot add().");
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot remove().");
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot retain().");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("TestingList is immutable. Cannot clear().");
        }
    }
}