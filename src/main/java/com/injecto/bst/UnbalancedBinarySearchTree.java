package com.injecto.bst;

import javax.annotation.Nullable;

public class UnbalancedBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
    @Nullable
    protected Node<K, V> root;

    @Override
    public void add(K key, @Nullable V value) {
        root = doAdd(root, key, value, 0);
    }

    /**
     * TODO why do I need level traversal in BST?
     */
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

    /**
     * @param node Node to new pair addition
     * @param key Key
     * @param value Value
     * @param depth Depth of node
     * @return Possible reassigned reference to node
     */
    protected Node<K, V> doAdd(@Nullable Node<K, V> node, K key, @Nullable V value, int depth) {
        if (node == null) {
            return new Node<>(key, value, depth);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.lesser = doAdd(node.lesser, key, value, depth + 1);
        } else if (comparison > 0) {
            node.larger = doAdd(node.larger, key, value, depth + 1);
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
