public class Node {

    public double x;
    public double y;
    public Node left;
    public Node right;
    public int level;

    public Node(double x, double y){
        this.x = x;
        this.y = y;
        this.level = 0;
    }

    public Node(double x, double y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public static double compare(Node node, Node loc,  int level) {
        if(level % 2 == 1) {
            return node.y - loc.y;
        } else {
            return node.x - loc.x;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Node))
            return false;
        Node n = (Node)o;
        return Math.abs(x - n.x) < 0.00000001 && Math.abs(y - n.y) < 0.00000001;
    }
}
