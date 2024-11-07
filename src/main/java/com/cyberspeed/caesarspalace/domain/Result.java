package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class Result {
    private SymbolName[][] matrix;
    private BigDecimal reward;
    @JsonProperty("applied_winning_combinations")
    private Map<SymbolName, List<WinCombinationName>> appliedWinningCombinations;
    @JsonProperty("applied_bonus_symbol")
    private SymbolName appliedBonusSymbol;
}
