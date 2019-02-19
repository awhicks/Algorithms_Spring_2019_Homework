public class BirdScooters {

    Node root;

    /**
     * Constructor
     */
    public BirdScooters(){
        root = null;
    }

    /**
     * Adds a scooter store at the Node passed in
     * @param scooter - location of the scooter store
     * @return true if added
     */
    public boolean add (Node scooter){
        // TODO implement this yourself
        return false;
    }

    /**
     * Find the closest scooter to the provided location
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    public Node closestPoint(Node location){
        // TODO implement this yourself
        return null;
    }

    /***************************************************************************
     *  Helper function for Distance Formula
     ***************************************************************************/

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]){

    }

}
