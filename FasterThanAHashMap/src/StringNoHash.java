import java.util.BitSet;


/**
 * A wwrapper class that hids the hashing functionality from
 * Hashmap (So it will has has it as an object)
 * and have to compute it hash function
 */

public class StringNoHash{
    public String s;

    StringNoHash( String s){
        this.s = s;
    }


    public int length() {
        return this.s.length();
    }

    public Character charAt(int i) {
        return this.s.charAt(i);
    }

    public String substring(int i) {
        return s.substring(i);
    }

    /**
     * Called by java hashFunction
     * @return
     */
    @Override
    public int hashCode(){

        int hash = 0;
        for (int i = 0; i < this.s.length(); i++)
            hash = this.s.charAt(i) + (31 * hash);
        return hash;
    }

    @Override public boolean equals(Object object) {
        return this.s.equals(((StringNoHash)object).s);
    }
}
