//package warmup;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static junit.framework.TestCase.assertEquals;

public class LRUTestRunner {
    /**
     * Test Double linked LRU
     */
    @Test(timeout = 1000)
    public void testSimpleLRU() {
        LRULinkedList lruLinkedList1 = new LRULinkedList(1);
        lruLinkedList1.add(1, 2);
        assertEquals(lruLinkedList1.getNode(1).value, 2);
        lruLinkedList1.add(2, 3);
        assertEquals(lruLinkedList1.getNode(1), null);

    }

    /**
     * Test example from the instructions
     */
    @Test(timeout = 1000)
    public void testExample() {
        LRUConstantTime cache = new LRUConstantTime(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(cache.get(1), 1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        assertEquals(cache.get(2), -1);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertEquals(cache.get(1), -1);       // returns -1 (not found)
        assertEquals(cache.get(3), 3);       // returns 3
        assertEquals(cache.get(4), 4);       // returns 4

    }


    @Test(timeout=5000)
    public void testRandomLRU() {
        for (int cap = 10; cap < 1000; cap++) {
            LRULinkedList ll = new LRULinkedList(cap);
            Stack<Integer> used = new Stack<>();
            for (int i = 0; i < cap; i++)
                used.push(i);
            Collections.shuffle(used);
            for (int u : used)
                ll.add(u, u);
            Collections.shuffle(used);
            Stack<Integer> evicted = new Stack<>();
            for (int i = 0; i < cap/2; i++)
                evicted.push(used.pop());
            for (int u : used)
                ll.getNode(u);
            for (int i = cap; i < cap + cap/2; i++)
                ll.add(i, i);
            for (int e : evicted)
                assertNull("getNode returned a Node that should have been evicted.",ll.getNode(e));
            Collections.shuffle(used);
            for (int u : used)
                assertNotNull("getNode returned null for a Node that should exist",ll.getNode(u));
            for (int i = cap; i < cap + cap/2; i++)
                assertNotNull("getNode returned null for a Node that should exist",ll.getNode(i));
        }
    }

    @Test(timeout = 1000)
    public void testSimpleLRULarger() {
        LRULinkedList lruLinkedList2 = new LRULinkedList(3);
        lruLinkedList2.add(1, 2);
        lruLinkedList2.add(2, 3);
        assertEquals(lruLinkedList2.getNode(1).value, 2);
        lruLinkedList2.add(3, 4);
        lruLinkedList2.add(4, 5);
        assertNull(lruLinkedList2.getNode(2));
        assertEquals(lruLinkedList2.getNode(4).value, 5);
    }

    @Test(timeout = 5000)
    public void testPut() {
        int capacity = 10000;
        LRUConstantTime cache  = new LRUConstantTime(capacity);
        int lastRandom = 0;
        int i;
        for (i = 0; i < capacity*200; i++) {
            lastRandom = (int) (Math.random() * 2500);
            cache.put(i, lastRandom);
        }

        assertEquals(cache.get(i - 1), lastRandom);
    }


}
