package com.cyberspeed.caesarspalace.util;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Args {
    private final String configPath;
    private final BigDecimal bettingAmount;
}
