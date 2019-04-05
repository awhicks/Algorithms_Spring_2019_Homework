import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FasterThanAHashMapTest {


    /**
     * These test cases don't use String for the hashMap of the Try
     * Since java precompute the hashfunction.
     *
     *
     */



    public String path1 = "src/BleakHouse.txt";
    public String path2 = "src/mobydick.txt";
    public String path3 = "src/randomLetters.txt";


    /**
     * Demonstrates that Tries are faster
     */
    @Test
    public void testSpeedA(){
        testSpeed(path2, path1, 10);
    }



    /**
     * Test is simpile compare the performance of the Trie and the hastMap.
     * The Trie should be faster
     *  Remember that you need to add optimizations.
     */

    public void testSpeed(String path1, String path2, int runs){

        double start;
        double stop;
        double diff1 =0;
        double diff2 =0;

        double ratio1 = 0;
        double ratio2 =0;




        //Run the Map

        start = System.currentTimeMillis();
        ratio1 = percentageInCommonMap(path1, path2, runs);

        stop = System.currentTimeMillis();

        diff1 = stop - start;


        //Run the Trie

        start = System.currentTimeMillis();

        ratio2 = percentageInCommonTrie(path1, path2, runs);

        stop = System.currentTimeMillis();
        diff2 = stop -start;




        double speedDifference = Math.abs(diff2-diff1);
        System.out.println("Time Trie: "+ diff2 +" Time Map: " + diff1);

        if(diff2 > diff1){
            System.out.println( "Slower By " + speedDifference +" ms "  + " ratio of hits to misses "
                    + Math.round(ratio1)
                    + " & " +  Math.round(ratio2)  );
        }else{
            System.out.println( "Faster By " + speedDifference +" ms"  + " ratio of hits to misses "
                    + Math.round(ratio1)
                    + " & " +  Math.round(ratio2)  );
        }

        /**
         System.out.println("Total Time for Trie " + diff2/runs +" ms");
         System.out.println("Ratio of hits to misses "+ratio1/runs);


         System.out.println("Total Time for HashMap " + diff1/runs +" ms");
         System.out.println("Ratio of hits to misses "+ratio2/runs);

         */

        assert(Math.round(ratio2 ) == Math.round(ratio1));


        assert (diff2 < diff1);
    }


    /**
     * This function modifies the string parmetter which is bad practice don't do this
     * @param sCurrentLine
     * @return
     */
    public static String[] processString(String sCurrentLine){

        sCurrentLine =  sCurrentLine.replaceAll("\\s+", " ").replaceAll("[^a-zA-Z\\s]", "").trim();
        if(sCurrentLine.isEmpty())
            return null;
        return sCurrentLine.toLowerCase().split(" ");
    }


    /**
     * This function computes number of words that overlap.
     * It will return a Percentage and Time object that
     * has both the amount of time that it took to generate the result as well
     * the percentage of works that both files have in common.
     * @param path1 : path to first file
     * @param path2 : path to the second file
     * @return ratio of hits to misses
     */
    public static double percentageInCommonMap(String path1, String path2, int runs) {

        HashMap<StringNoHash, String> dictionaryOfWords = new HashMap<>();

        // Build up a hashmap of matching words.
        String sCurrentLine = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {

            while ((sCurrentLine = br.readLine()) != null) {
                String[] sCurrentLineArray = processString(sCurrentLine);
                if(sCurrentLineArray!=null) {
                    for (String s : sCurrentLineArray) {
                        if(!s.equals(""))
                            dictionaryOfWords.put(new StringNoHash(s), "True");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Read in the second file and check see number of matches.
        double ratio = 0;

        for(int i =0; i < runs; i++) {
            double count = 0;
            double hit = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {

                while ((sCurrentLine = br.readLine()) != null) {
                    String[] sCurrentLineArray = processString(sCurrentLine);
                    if (sCurrentLineArray != null) {
                        for (String s : sCurrentLineArray) {
                            if (!s.equals(""))
                                if (dictionaryOfWords.containsKey(new StringNoHash(s))) {
                                    hit++;
                                }
                            count++;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ratio += hit * 100 /count;
        }


        return  ratio/runs;


    }





    /**
     * Same code as before except the hashmap has been replace by a trie.
     * The
     */

    public static double percentageInCommonTrie(String path1, String path2, int runs) {

        OptimizedTST<String> dictionaryOfWords = new OptimizedTST<>();

        // Build up a hashmap of matching words.
        String sCurrentLine = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {

            while ((sCurrentLine = br.readLine()) != null) {
                String[] sCurrentLineArray = processString(sCurrentLine);
                if (sCurrentLineArray != null) {
                    for (String s : sCurrentLineArray) {
                        if (!s.equals(""))
                            dictionaryOfWords.put(s, "True");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Read in the second file and check see number of matches.
        double ratio = 0;
        for (int i = 0; i < runs; i++) {
            double count = 0;
            double hit = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                while ((sCurrentLine = br.readLine()) != null) {
                    String[] sCurrentLineArray = processString(sCurrentLine);
                    if (sCurrentLineArray != null) {
                        for (String s : sCurrentLineArray) {
                            if (!s.equals(""))
                                if (dictionaryOfWords.contains(s)) {
                                    hit++;
                                }
                            count++;

                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            ratio += hit * 100 / count;
        }

        return ratio/runs;
    }





}




