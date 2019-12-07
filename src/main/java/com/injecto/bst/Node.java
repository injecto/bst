package com.injecto.bst;

import javax.annotation.Nullable;

class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    @Nullable Node<K, V> lesser;
    @Nullable Node<K, V> larger;
    int height = 1;
    int depth;

    Node(K key, V value, int depth) {
        this.key = key;
        this.value = value;
        this.depth = depth;
    }

    Node<K, V> rightRotate() {
        var x = lesser;
        var t2 = x.larger;

        x.larger = this;
        lesser = t2;

        height = Math.max(Utils.height(lesser), Utils.height(larger)) + 1;
        x.height = Math.max(Utils.height(x.lesser), Utils.height(x.larger)) + 1;

        x.setDepth(x.depth - 1);
        return x;
    }

    Node<K, V> leftRotate() {
        var x = larger;
        var t2 = x.lesser;

        x.lesser = this;
        larger = t2;

        height = Math.max(Utils.height(lesser), Utils.height(larger)) + 1;
        x.height = Math.max(Utils.height(x.lesser), Utils.height(x.larger)) + 1;

        x.setDepth(x.depth - 1);
        return x;
    }

    void setDepth(int depth) {
        this.depth = depth;
        if (lesser != null) {
            lesser.setDepth(depth + 1);
        }
        if (larger != null) {
            larger.setDepth(depth + 1);
        }
    }

    int dumpTo(StringDump dump, int offset) {
        int offsetAfterLeft = lesser == null ? offset : lesser.dumpTo(dump, offset);
        var keyStr = key.toString();
        dump.dump(keyStr, offsetAfterLeft, depth);
        int offsetAfterKey = offsetAfterLeft + keyStr.length();
        return larger == null ? offsetAfterKey : larger.dumpTo(dump, offsetAfterKey);
    }

    @Override
    public String toString() {
        return key + " -> " + value;
    }
}
