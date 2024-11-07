package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WinCombinationName {
    SAME_SYMBOL_3_TIMES("same_symbol_3_times"),
    SAME_SYMBOL_4_TIMES("same_symbol_4_times"),
    SAME_SYMBOL_5_TIMES("same_symbol_5_times"),
    SAME_SYMBOL_6_TIMES("same_symbol_6_times"),
    SAME_SYMBOL_7_TIMES("same_symbol_7_times"),
    SAME_SYMBOL_8_TIMES("same_symbol_8_times"),
    SAME_SYMBOL_9_TIMES("same_symbol_9_times"),
    SAME_SYMBOLS_HORIZONTALLY("same_symbols_horizontally"),
    SAME_SYMBOLS_VERTICALLY("same_symbols_vertically"),
    SAME_SYMBOLS_DIAGONALLY_LTR("same_symbols_diagonally_left_to_right"),
    SAME_SYMBOLS_DIAGONALLY_RTL("same_symbols_diagonally_right_to_left"),
    ;

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
