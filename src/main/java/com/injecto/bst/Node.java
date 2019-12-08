package com.injecto.bst;

import javax.annotation.Nullable;

class Node<K extends Comparable<K>, V> {
    K key;
    @Nullable V value;
    @Nullable Node<K, V> lesser;
    @Nullable Node<K, V> larger;
    /**
     * Height of the subtree with root in this node
     */
    int height = 1;
    /**
     * Depth of this node from tree root, started with 0
     */
    int depth;

    Node(K key, @Nullable V value, int depth) {
        this.key = key;
        this.value = value;
        this.depth = depth;
    }

    Node<K, V> rightRotate() {
        var willBeHigher = lesser;
        var toRebind = willBeHigher.larger;

        willBeHigher.larger = this;
        lesser = toRebind;

        height = Math.max(Utils.height(lesser), Utils.height(larger)) + 1;
        willBeHigher.height = Math.max(Utils.height(willBeHigher.lesser), Utils.height(willBeHigher.larger)) + 1;

        willBeHigher.setDepth(willBeHigher.depth - 1);
        return willBeHigher;
    }

    Node<K, V> leftRotate() {
        var willBeHigher = larger;
        var toRebind = willBeHigher.lesser;

        willBeHigher.lesser = this;
        larger = toRebind;

        height = Math.max(Utils.height(lesser), Utils.height(larger)) + 1;
        willBeHigher.height = Math.max(Utils.height(willBeHigher.lesser), Utils.height(willBeHigher.larger)) + 1;

        willBeHigher.setDepth(willBeHigher.depth - 1);
        return willBeHigher;
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
