package com.injecto.bst;

import javax.annotation.Nullable;

public class Utils {
    static int height(@Nullable Node<?, ?> node) {
        return node == null ? 0 : node.height;
    }

    static int balance(@Nullable Node<?, ?> node) {
        return node == null ? 0 : height(node.lesser) - height(node.larger);
    }
}
