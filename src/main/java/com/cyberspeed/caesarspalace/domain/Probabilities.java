package com.cyberspeed.caesarspalace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<Probability> standardSymbols;
    @JsonProperty("bonus_symbols")
    private Probability bonusSymbols;
}
