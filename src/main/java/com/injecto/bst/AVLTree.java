package com.injecto.bst;

import javax.annotation.Nullable;

import static com.injecto.bst.Utils.balance;
import static com.injecto.bst.Utils.height;
import static java.lang.Math.max;

public class AVLTree<K extends Comparable<K>, V> extends UnbalancedBinarySearchTree<K, V> {
    @Override
    protected Node<K, V> add(@Nullable Node<K, V> node, K key, V value, int depth) {
        node = super.add(node, key, value, depth);
        node.height = max(height(node.lesser), height(node.larger)) + 1;
        var balance = balance(node);

        if (balance > 1) {
            if (key.compareTo(node.lesser.key) > 0) {
                node.lesser = node.lesser.leftRotate();
            }
            return node.rightRotate();
        }

        if (balance < -1) {
            if (key.compareTo(node.larger.key) < 0) {
                node.larger = node.larger.rightRotate();
            }
            return node.leftRotate();
        }

        return node;
    }
}
