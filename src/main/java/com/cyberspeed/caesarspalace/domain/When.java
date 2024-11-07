package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum When {
    SAME_SYMBOLS("same_symbols"),
    LINEAR_SYMBOLS("linear_symbols");

    private final String name;

    @JsonValue
    public String getName() {
        return this.name;
    }
}
