package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Symbol {
    @JsonProperty("reward_multiplier")
    private BigDecimal rewardMultiplier;
    private BigDecimal extra;
    private SymbolType type;
    private Impact impact;
}
