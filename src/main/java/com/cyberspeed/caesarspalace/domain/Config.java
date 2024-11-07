package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private Integer columns;
    private Integer rows;
    private Map<SymbolName, Symbol> symbols;
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<WinCombinationName, WinCombination> winCombinations;
}
