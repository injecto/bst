package com.injecto.bst;

import org.junit.Test;

import java.util.List;

import static com.injecto.bst.Utils.addAll;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UnbalancedBinarySearchTreeTest {
    @Test
    public void testAddAndSearch() {
        // arrange
        var bst = new UnbalancedBinarySearchTree<Integer, String>();

        // act
        bst.add(1, "one");
        bst.add(2, "two");
        bst.add(0, "zero");
        var result1 = bst.search(1);
        var result2 = bst.search(2);
        var result0 = bst.search(0);

        // assert
        assertEquals("one", result1);
        assertEquals("two", result2);
        assertEquals("zero", result0);
    }

    @Test
    public void complexityTest() {
        // arrange
        var bst = new UnbalancedBinarySearchTree<Integer, String>();
        var keys = List.of(1, 2, 3, 4, 5);
        addAll(bst, keys);
        var spy = spy(bst);

        // act
        spy.add(6, null);

        // assert
        verify(spy, times(5)).doAdd(any(Node.class), eq(6), eq(null), anyInt());
    }

    @Test
    public void dumpOfEmptyTreeTest() {
        // arrange
        var bst = new UnbalancedBinarySearchTree<Integer, String>();

        // act
        var dump = bst.toString();

        // assert
        assertEquals("<empty>", dump);
    }


    @Test
    public void dumpTest() {
        // arrange
        var bst = new UnbalancedBinarySearchTree<Integer, String>();
        var keys = List.of(1, 2, 3, 4, 5, 6, 7);
        addAll(bst, keys);

        // act
        var dump = bst.toString();

        // assert

        var lines = dump.split("\n");
        assertEquals(7, lines.length);
        for (Integer key : keys) {
            String level = lines[key - 1];
            assertThat(level, containsString(key.toString()));
        }
    }
}
