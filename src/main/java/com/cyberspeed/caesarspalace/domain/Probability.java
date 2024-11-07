package com.cyberspeed.caesarspalace.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Probability {
    private Integer column;
    private Integer row;
    private Map<SymbolName, Integer> symbols;
}
