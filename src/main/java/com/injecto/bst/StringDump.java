package com.injecto.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class StringDump {
    private List<StringBuilder> levels = new ArrayList<>();

    void dump(String str, int offset, int depth) {
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
