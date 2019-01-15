public class FriendCircles {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i

    /**
     *
     * @param M the adjacency matrix of friends
     * @return number of friend circles
     */
    public int findCircleNum(int[][] M) {
        // TODO: implement this method
        return 0;
    }


    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        // TODO: implement this method
        return 0;
    }


    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        // TODO: implement this method
        return false;
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        // TODO: implement this method
    }

    public static void main(String[] args) {

    }

}
