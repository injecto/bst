package com.injecto.bst;

import org.junit.Test;

import java.util.List;

public class SimpleBinarySearchTreeTest {
    @Test
    public void testDump() {
        // arrange
        var bst = new SimpleBinarySearchTree<Integer, Void>();

        var keys = List.of(100, 15, 190, 171, 3, 91, 205, 155, 13, 17, 203);
        for (Integer key : keys) {
            bst.add(key, null);
        }

        // act
        var dump = bst.toString();

        // assert
        System.out.println(dump);
    }

    @Test
    public void testBalance() {
        // arrange
        var bst = new SimpleBinarySearchTree<Integer, Void>();

        var keys = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        for (Integer key : keys) {
            bst.add(key, null);
        }

        // act
        var dump = bst.toString();

        // assert
        System.out.println(dump);
    }
}
