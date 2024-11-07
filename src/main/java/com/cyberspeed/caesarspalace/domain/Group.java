package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Group {
    SAME_SYMBOLS("same_symbols"),
    HORIZONTALLY_LINEAR_SYMBOLS("horizontally_linear_symbols"),
    VERTICALLY_LINEAR_SYMBOLS("vertically_linear_symbols"),
    LTR_DIAGONALLY_LINEAR_SYMBOLS("ltr_diagonally_linear_symbols"),
    RTL_DIAGONALLY_LINEAR_SYMBOLS("rtl_diagonally_linear_symbols"),
    ;

    private final String name;

    @JsonValue
    public String getName() {
        return this.name;
    }
}
