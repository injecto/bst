package com.injecto.bst;

import javax.annotation.Nullable;

public class UnbalancedBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
    @Nullable
    private Node<K, V> root;

    @Override
    public void add(K key, V value) {
        root = add(root, key, value, 0);
    }

    @Nullable
    @Override
    public V search(K key) {
        var current = root;
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }

            if (key.compareTo(current.key) < 0) {
                current = current.lesser;
            } else {
                current = current.larger;
            }
        }

        return null;
    }

    protected Node<K, V> add(@Nullable Node<K, V> node, K key, V value, int depth) {
        if (node == null) {
            return new Node<>(key, value, depth);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.lesser = add(node.lesser, key, value, depth + 1);
        } else if (comparison > 0) {
            node.larger = add(node.larger, key, value, depth + 1);
        } else {
            node.value = value;
            return node;
        }

        return node;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "<empty>";
        }

        var dump = new StringDump();
        root.dumpTo(dump, 0);
        return dump.toString();
    }
}
