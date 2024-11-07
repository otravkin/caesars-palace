package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SymbolType {
    STANDARD("standard"),
    BONUS("bonus");

    private final String name;

    @JsonValue
    public String getName() {
        return this.name;
    }
}
