
public class OptimizedTST<Value> {

    private int n;              // size
    private Node<Value> root;   // root of TST


    private static class Node<Value> {
        private char c;                        // character
        private Node<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }


    public boolean contains(String key) {
        return false;

    }

    public Value get(String key){
        return null;


    }

    public boolean put(String key, String value){
        return false;

    }

}
