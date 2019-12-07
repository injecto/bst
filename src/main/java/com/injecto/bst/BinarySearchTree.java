package com.injecto.bst;

import javax.annotation.Nullable;

public interface BinarySearchTree<K extends Comparable<K>, V> {
    void add(K key, V value);

    @Nullable
    V search(K key);
}
