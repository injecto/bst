package com.injecto.bst;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
    @Nullable
    private Node<K, V> root;

    @Nullable
    @Override
    public V add(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value, 0);
            return null;
        }

        var current = root;
        while (true) {
            if (current.key.equals(key)) {
                return current.setValue(value);
            }

            if (key.compareTo(current.key) < 0) {
                if (current.lesser == null) {
                    current.lesser = new Node<>(key, value, current.depth + 1);
                    return null;
                } else {
                    current = current.lesser;
                }
            } else {
                if (current.larger == null) {
                    current.larger = new Node<>(key, value, current.depth + 1);
                    return null;
                } else {
                    current = current.larger;
                }
            }
        }
    }

    @Nullable
    @Override
    public V search(K key) {
//        if (root == null) {
//            return null;
//        }
//
//        var searchQueue = new ArrayDeque<>(Collections.singleton(root));
//        Node<K, V> current;
//        while ((current = searchQueue.poll()) != null) {
//            if (current.key.equals(key)) {
//                return current.value;
//            }
//
//            if (current.lesser != null) {
//                searchQueue.add(current.lesser);
//            }
//            if (current.larger != null) {
//                searchQueue.add(current.larger);
//            }
//        }
//
//        return null;

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

    @Override
    public String toString() {
        if (root == null) {
            return "<empty>";
        }

        var dump = new StringDump();
        root.dumpTo(dump, 0);
        return dump.toString();
    }

    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        @Nullable
        private Node<K, V> lesser;
        @Nullable
        private Node<K, V> larger;
        private int depth;

        private Node(K key, V value, int depth) {
            this.key = key;
            this.value = value;
            this.depth = depth;
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
