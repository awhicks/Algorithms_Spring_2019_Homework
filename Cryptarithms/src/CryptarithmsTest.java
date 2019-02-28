import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class CryptarithmsTest {

    /**
     * This takes in the challenge and the answer from the student solution and checks to see if the solution is valid
     * NOTE: only works if the answer isn't NULL
     * @param cryptChall
     * @param candAns
     * @return
     */
    public static boolean isSolValid(List<String> cryptChall, Map<Character, Integer> candAns) {
        int answer = 0;
        int sumNum = 0;
        String sum = cryptChall.remove(cryptChall.size() - 1);
        int lengthOfSum = sum.length();

        // Calculates the sum
        for (int i = 0; i < lengthOfSum; i++) {
            sumNum += candAns.get(sum.charAt(i)) * (Math.pow(10, lengthOfSum - 1 - i));
        }

        // loops through the list of strings
        for (int i = 0; i < cryptChall.size(); i++) {
            String current = cryptChall.get(i);
            int lenCurrent = current.length();
            // loops through current string and computes sum on that smaller number
            for (int j = 0; j < lenCurrent; j++) {
                answer += candAns.get(current.charAt(j)) * Math.pow(10, lenCurrent - 1 - j);
            }
        }
        boolean ret = answer == sumNum;
        if (!ret) {
            System.out.println(candAns + " incorrect for ");
            System.out.println(cryptChall + " = " +sum);
        }
        return ret;
    }

    @Test(timeout=200)
    public void solveSendMoreMoney() {
        List<String> input = new ArrayList<>();
        input.add("SEND");
        input.add("MORE");
        input.add("MONEY");
        assertTrue(isSolValid(input, Cryptarithms.solve(input)));
    }

    @Test(timeout=200)
    public void planets1() {
        List<String> input = new ArrayList<>();
        input.add("MARS");
        input.add("SATURN");
        input.add("URANUS");
        input.add("MERCURY");

        assertTrue(isSolValid(input, Cryptarithms.solve(input)));

    }

    @Test(timeout=200)
    public void planets2() {
        List<String> input = new ArrayList<>();
        input.add("SATURN");
        input.add("URANUS");
        input.add("JUPITER");

        assertTrue(isSolValid(input, Cryptarithms.solve(input)));

    }

    @Test(timeout=200)
    public void planets3() {
        List<String> input = new ArrayList<>();
        input.add("VENUS");
        input.add("EARTH");
        input.add("URANUS");
        input.add("SATURN");

        assertTrue(isSolValid(input, Cryptarithms.solve(input)));

    }

    @Test(timeout=200)
    public void planets4() {
        List<String> input = new ArrayList<>();
        input.add("EARTH");
        input.add("URANUS");
        input.add("SATURN");

        assertTrue(isSolValid(input, Cryptarithms.solve(input)));
    }

    @Test(timeout=2000)
    public void planets5Null() {
        List<String> input = new ArrayList<>();
        input.add("VENUS");
        input.add("MARS");
        input.add("SATURN");
        input.add("URANUS");
        assertEquals(null, Cryptarithms.solve(input));
    }

    public static Set<Character> getAlphabet() {
        Set<Character> out = new HashSet<>();
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alph.length(); i++) {
            out.add(alph.charAt(i));
        }
        return out;
    }

    public static <T> T setPop(Set<T> s) {
        Object[] a = s.toArray();
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        T t = (T)a[rnd.nextInt(a.length)];
        s.remove(t);
        return t;
    }


    /**
     * Outputted problem is guaranteed to have a solution
     *
     * @param numAddends
     * @param maxAddend
     * @return
     */
    public static List<String> getRandProblem(int numAddends, long maxAddend) {
        //System.out.println(maxAddend);
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        long[] addends = new long[numAddends];
        for (int i = 0; i < numAddends; i++)
            addends[i] = rnd.nextLong(maxAddend - 1) + 1;
        long sum = 0;
        for (long i : addends)
            sum += i;

        List<String> numberStrings = new ArrayList<>();
        for (long a : addends)
            numberStrings.add("" + a);
        numberStrings.add("" + sum);
        //System.out.println(numberStrings);

        return encodeNumberStrings(numberStrings);
    }


    public static List<String> encodeNumberStrings(List<String> numberStrings) {
        Set<Integer> uniqueDigits = new HashSet<>();
        for (String s : numberStrings)
            for (int i = 0; i < s.length(); i++)
                uniqueDigits.add(s.charAt(i) - '0');
        //System.out.println(uniqueDigits);


        Set<Character> alphabet = getAlphabet();
        Map<Integer, Character> m = new HashMap<>();
        while (!uniqueDigits.isEmpty())
            m.put(setPop(uniqueDigits), setPop(alphabet));

        List<String> problem = new ArrayList<>();
        for (String s : numberStrings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++)
                sb.append(m.get(s.charAt(i) - '0'));
            problem.add(sb.toString());
        }
        return problem;
    }



    @Test(timeout=20000)
    public void randomTest() {
        ThreadLocalRandom randomness = ThreadLocalRandom.current();
        int width = 10;
        int height = 10;

        for (int i = 1; i < width; i++) {
            for (int h = 2; h < height; h++) {
                List<String> problem = getRandProblem(h, Math.round(Math.pow(10, i)));

                assertTrue(
                        isSolValid(
                                problem,
                                Cryptarithms.solve(
                                        Collections.unmodifiableList(
                                                problem
                                        )
                                )
                        )
                );
            }
        }
    }


    public String getNums(int duplicates, boolean leading) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 10; i++)
            counts.put(i, duplicates);

        StringBuilder out = new StringBuilder();
        while (!counts.isEmpty()) {
            Object[] keys = counts.keySet().toArray();
            int n = (int)keys[rnd.nextInt(keys.length)];
            counts.replace(n, counts.get(n) -1);
            if (counts.get(n) <= 0)
                counts.remove(n);
            out.append(n);
        }
        if (leading) {
            for (int i = 0; i < out.length(); i++)
                if (out.charAt(i) == '0') {
                    out.replace(i, i + 1, "" + (rnd.nextInt(9) + 1));
                }
        }
        return out.toString();
    }


    @Test(timeout = 20000)
    public void runtimeTest() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int d = 4;

        List<String> nums = new ArrayList<>();
        for (int i = 0; i < 10*d; i++) {
            nums.add(getNums(d, i==0));
        }

        List<String> numberStrings = new ArrayList<>();
        for (int i = 0; i < 10*d; i++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < nums.size(); c++)
                sb.append(nums.get(c).charAt(i));
            numberStrings.add(sb.toString());
        }
        numberStrings.remove(rnd.nextInt(10*d));

        BigInteger sum = new BigInteger("0");
        for (String s : numberStrings) {
            sum = sum.add(new BigInteger(s));
            //System.out.println(s);
        }
        numberStrings.add(sum.toString());
        //System.out.println(sum);
        List<String> problem = encodeNumberStrings(numberStrings);
        //for (String s : problem)
        //    System.out.println(s);
        assertTrue(
                isSolValid(
                        problem,
                        Cryptarithms.solve(
                                Collections.unmodifiableList(
                                        problem
                                )
                        )
                )
        );
    }


}
