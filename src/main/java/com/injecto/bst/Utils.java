package com.injecto.bst;

import javax.annotation.Nullable;
import java.util.Collection;

public class Utils {
    static int height(@Nullable Node<?, ?> node) {
        return node == null ? 0 : node.height;
    }

    /**
     * AVLs tree balance factor of node
     */
    static int balance(@Nullable Node<?, ?> node) {
        return node == null ? 0 : height(node.lesser) - height(node.larger);
    }

    static <K extends Comparable<K>> void addAll(BinarySearchTree<K, ?> bst, Collection<K> keys) {
        for (K key : keys) {
            bst.add(key, null);
        }
    }
}
