import java.util.*;

public class twitterMap {
    private int numberOfPeople;
    private int follows;
    private LinkedList<Integer> twitterScrapeResults[];

    /**
     * The constructor of the twitter map
     * Takes in the number of people and creates a corresponding adjency matrix.
     * @param numberOfPeople
     */
    twitterMap(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        twitterScrapeResults = new LinkedList[numberOfPeople];
        this.follows = 0;
        for (int i = 0; i< numberOfPeople; ++i)
            twitterScrapeResults[i] = new LinkedList();
    }

    /**
     * This function adds the personBeingFollowed to the list of people the follower is following. It does this by going
     * to the twitterScrapeResults adjacency matrix of the follower and adds the integer representation of
     * personBeingFollowed to that matrix.
     * @param follower
     * @param personBeingFollowed
     */
    public void addFollower(int follower, int personBeingFollowed) {
        twitterScrapeResults[follower].add(personBeingFollowed);
    }

    /**
     * This returns a list of the integers that represent the people required to tweet to everyone.
     * TODO: Implement this method using whatever strategy you want. Runtime should be O(V + E).
     * TODO: Have fun and good luck!
     */
    public  List<Integer> findKPeople() {
        List<Integer> answer = new ArrayList<Integer>();
        return answer;
    }

    /**
     * added to compile
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Welcome to Rohit's bonus problem!");
    }
}

