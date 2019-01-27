public class NthPlaceLoser {


    /**
     * TODO: Implement this function using any helper functions you deem necessary.
     *
     */
    public static Student NthPlaceLoser(Student[] aList, int N) {

        return aList[0];
    }

    /***************************************************************************
     *  Helper sorting functions.
     *********************************************************************git******/

    // is v < w ?
    private static boolean less(Student v, Student w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {

    }

}

