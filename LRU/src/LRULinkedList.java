public class LRULinkedList {
    public Node head;
    public Node tail;
    public int capacity =0;
    public int size =0;

    /**
     * Constructor creates a double linked list with a single value.
     */
    public LRULinkedList(int capacity){
        this.capacity = capacity;
    }

    /**
     * Add a node to the linked list.
     * @param key
     * @param value
     * @return new node
     */
    public Node add(int key, int value) {
        // TODO: implement this method
        return head;
    }

    /**
     * Remove the last item in the linked list.
     */
    public void deleteTail(){
        // TODO: implement this method
    }

    /**
     * Get the value of the current node.
     * @param key
     * @return value at key
     */
    public Node getValue(int key){
        // TODO: implement this method
        return head;
    }

    /**
     * Move the passed in node to the head position.
     * @param node
     */
    public void moveNodeToHead(Node node){
        // TODO: implement this method
    }

    public static void main(String[] args) {

    }

}
