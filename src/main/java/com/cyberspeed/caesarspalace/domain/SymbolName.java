package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SymbolName {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    MUL_10("10x"),
    MUL_5("5x"),
    PLUS_1000("+1000"),
    PLUS_500("+500"),
    MISS("MISS")
    ;

    private final String name;

    @JsonValue
    public String getName() {
        return this.name;
    }
}
