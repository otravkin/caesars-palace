package com.cyberspeed.caesarspalace.domain;

import com.cyberspeed.caesarspalace.domain.deserializer.CoordinatesDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class WinCombination {
    @JsonProperty("reward_multiplier")
    private BigDecimal rewardMultiplier;
    private Integer count;
    private When when;
    private Group group;
    @JsonProperty("covered_areas")
    @JsonDeserialize(using = CoordinatesDeserializer.class)
    private List<List<Coordinate>> coveredAreas;
}
