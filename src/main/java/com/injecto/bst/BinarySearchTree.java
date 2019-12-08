package com.injecto.bst;

import javax.annotation.Nullable;

public interface BinarySearchTree<K extends Comparable<K>, V> {
    /**
     * Add Key -> Value pair to tree. If key already exists, paired value will be changed.
     */
    void add(K key, @Nullable V value);

    /**
     * Search paired value by key.
     * @return Value or null if key isn't exists or value is null.
     */
    @Nullable
    V search(K key);
}
