package com.injecto.bst;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;

public class SimpleBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
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

    private Node<K, V> add(@Nullable Node<K, V> node, K key, V value, int depth) {
        if (node == null) {
            return new Node<>(key, value, depth);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.lesser = add(node.lesser, key, value, depth + 1);
        } else if (comparison > 0) {
            node.larger = add(node.larger, key, value, depth + 1);
        } else {
            node.setValue(value);
            return node;
        }

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

    @Override
    public String toString() {
        if (root == null) {
            return "<empty>";
        }

        var dump = new StringDump();
        root.dumpTo(dump, 0);
        return dump.toString();
    }

    private static int height(@Nullable Node<?, ?> node) {
        return node == null ? 0 : node.height;
    }

    private static int balance(@Nullable Node<?, ?> node) {
        return node == null ? 0 : height(node.lesser) - height(node.larger);
    }

    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        @Nullable
        private Node<K, V> lesser;
        @Nullable
        private Node<K, V> larger;
        private int height = 1;
        private int depth;

        private Node(K key, V value, int depth) {
            this.key = key;
            this.value = value;
            this.depth = depth;
        }

        private Node<K, V> rightRotate() {
            var x = lesser;
            var t2 = x.larger;

            x.larger = this;
            lesser = t2;

            height = max(height(lesser), height(larger)) + 1;
            x.height = max(height(x.lesser), height(x.larger)) + 1;

            x.setDepth(x.depth - 1);
            return x;
        }

        private Node<K, V> leftRotate() {
            var x = larger;
            var t2 = x.lesser;

            x.lesser = this;
            larger = t2;

            height = max(height(lesser), height(larger)) + 1;
            x.height = max(height(x.lesser), height(x.larger)) + 1;

            x.setDepth(x.depth - 1);
            return x;
        }

        private void setDepth(int depth) {
            this.depth = depth;
            if (lesser != null) {
                lesser.setDepth(depth + 1);
            }
            if (larger != null) {
                larger.setDepth(depth + 1);
            }
        }

        @Nullable
        private V setValue(V value) {
            var old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return key + " -> " + value;
        }

        private int dumpTo(StringDump dump, int offset) {
            int offsetAfterLeft = lesser == null ? offset : lesser.dumpTo(dump, offset);
            var keyStr = key.toString();
            dump.dump(keyStr, offsetAfterLeft, depth);
            int offsetAfterKey = offsetAfterLeft + keyStr.length();
            return larger == null ? offsetAfterKey : larger.dumpTo(dump, offsetAfterKey);
        }
    }

    private static class StringDump {
        private List<StringBuilder> levels = new ArrayList<>();

        private void dump(String str, int offset, int depth) {
            var missingLevels = depth - levels.size() + 1;
            if (missingLevels > 0) {
                for (int i = 0; i < missingLevels; i++) {
                    levels.add(new StringBuilder());
                }
            }

            var level = levels.get(depth);
            int padding = offset - level.length();
            level.append(pad(padding)).append(str);
        }

        private String pad(int padding) {
            return IntStream.range(0, padding)
                    .mapToObj(__ -> " ")
                    .collect(Collectors.joining());
        }

        @Override
        public String toString() {
            return String.join("\n", levels);
        }
    }
}
