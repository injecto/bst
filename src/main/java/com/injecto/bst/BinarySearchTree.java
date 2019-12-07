package com.injecto.bst;

import javax.annotation.Nullable;

public interface BinarySearchTree<K extends Comparable<K>, V> {
    @Nullable
    V add(K key, V value);

    @Nullable
    V search(K key);
}
