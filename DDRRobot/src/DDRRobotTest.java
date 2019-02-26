import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DDRRobotTest {

    /**
     * __________________
     * | →  |   ↓  | →  |
     * |____|______|____|
     * | ↑  |  ← → | ↓  |
     * |____|______|____|
     * | ↑  |  ← → | ↑  |
     * |____|______|____|
     *
     * __________________
     * | 0  |   1  | 2  |
     * |____|______|____|
     * | 3  |  4   | 5  |
     * |____|______|____|
     * | 6  |  7   | 8  |
     * |____|______|____|
     *
     * key:
     * → 1
     * ↑ 2
     * ← 3
     * ↓ 4
     *
     * 0 : 1
     * 1 : 4
     * 2 : 1
     * 3 : 2
     * 4 : 3 , 1
     * 5 : 4
     * 6 : 2
     * 7 : 1, 3
     * 8 : 2
     */
    @Test(timeout=100)
    public void simpleTest(){
        DDRRobot dBot = new DDRRobot(9);
        dBot.addTile(0, 1);
        dBot.addTile(1, 4);
        dBot.addTile(2, 1);
        dBot.addTile(3, 2);
        dBot.addTile(4, 3);
        dBot.addTile(4, 1);
        dBot.addTile(5, 4);
        dBot.addTile(6, 2);
        dBot.addTile(7, 1);
        dBot.addTile(7, 3);
        dBot.addTile(8, 2);

        int[] result = {0, 1, 3, 4};
        ArrayList<Integer> answers = dBot.getPlayOptions();

        assert(answers.size() == result.length);
        for(int i =0; i < result.length; i++){
            assert(answers.contains(result[i]));
        }

    }


    /**
     * __________________
     * | →  |   ↓  | →  |
     * |____|______|____|
     * | ↑  |  ← → | ↓  |
     * |____|______|____|
     * | ↑  |  ← → | ←  |
     * |____|______|____|
     *
     * __________________
     * | 0  |   1  | 2  |
     * |____|______|____|
     * | 3  |  4   | 5  |
     * |____|______|____|
     * | 6  |  7   | 8  |
     * |____|______|____|
     *
     * key:
     * → 1
     * ↑ 2
     * ← 3
     * ↓ 4
     *
     * 0 : 1
     * 1 : 4
     * 2 : 1
     * 3 : 2
     * 4 : 3 , 1
     * 5 : 4
     * 6 : 2
     * 7 : 1, 3
     * 8 : 3
     */


    @Test(timeout=100)
    public void simpleTest2(){
        DDRRobot dBot = new DDRRobot(9);
        dBot.addTile(0, 1);
        dBot.addTile(1, 4);
        dBot.addTile(2, 1);
        dBot.addTile(3, 2);
        dBot.addTile(4, 3);
        dBot.addTile(4, 1);
        dBot.addTile(5, 4);
        dBot.addTile(6, 2);
        dBot.addTile(7, 1);
        dBot.addTile(7, 3);
        dBot.addTile(8, 3);

        int[] result = {0, 1, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> answers = dBot.getPlayOptions();
        assert(answers.size() == result.length);
        for(int i =0; i < result.length; i++){
            assert(answers.contains(result[i]));
        }

    }
}
