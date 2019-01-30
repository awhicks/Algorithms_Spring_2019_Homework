public class NthPlaceLoser {


    public static Student NthPlaceLoser(Student[] aList, int N) {
        // TODO implement this yourself
        return aList[0];
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    /**
     * This method returns whether a student is less than another student in respect
     * to how your compareTo method is defined
     * @param v First Student
     * @param w Second Student
     * @return if v (first student) is less than w (second student)
     */
    private static boolean less(Student v, Student w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    /**
     * This method exchanges a[i] and a[j] in an array
     * @param a array in which you want to swap
     * @param i index of first item
     * @param j index of second item
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String[] args) {

    }

}