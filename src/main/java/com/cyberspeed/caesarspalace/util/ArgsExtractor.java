package com.cyberspeed.caesarspalace.util;

import java.math.BigDecimal;

public class ArgsExtractor {

    private static final String CONFIG_PARAM = "--config";
    private static final String BETTING_AMOUNT_PARAM = "--betting-amount";

    public static Args extractArgs(final String[] args) {
        String configPath = null;
        BigDecimal bettingAmount = null;

        if (args.length == 0 || args.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        for (int i = 0; i < args.length; i++) {
            if (CONFIG_PARAM.equalsIgnoreCase(args[i]) && i < args.length - 1) {
                configPath = args[i + 1];
            }
            if (BETTING_AMOUNT_PARAM.equalsIgnoreCase(args[i]) && i < args.length - 1) {
                bettingAmount = new BigDecimal(args[i + 1]);
            }
        }

        if (configPath == null) {
            throw new IllegalArgumentException("Path to config is missing");
        } else if (bettingAmount == null) {
            throw new IllegalArgumentException("Betting amount is missing");
        }

        return new Args(configPath, bettingAmount);
    }
}
