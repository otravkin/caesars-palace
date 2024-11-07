package com.cyberspeed.caesarspalace.game;

import com.cyberspeed.caesarspalace.domain.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public class Game {
    private final Config config;
    private final AtomicReference<BigDecimal> rewardAtomic = new AtomicReference<>(BigDecimal.ZERO);
    private final Map<SymbolName, List<WinCombinationName>> appliedWinningCombinations = new TreeMap<>();
    private final Result result = new Result();

    public Result performSpin(final BigDecimal bettingAmount) {
        final SymbolName[][] matrix = new MatrixInitializer().initMatrix(config);

        final Map<SymbolName, Integer> frequencyMap = new TreeMap<>();
        for (SymbolName[] row : matrix) {
            for (SymbolName symbol : row) {
                frequencyMap.put(symbol, frequencyMap.getOrDefault(symbol, 0) + 1);
            }
        }

        // checking for same symbol n times combinations
        config.getWinCombinations().entrySet().stream()
                .filter((entry) -> entry.getValue().getWhen() == When.SAME_SYMBOLS)
                .forEach(winCombinationEntry -> frequencyMap.forEach((symbolName, freq) -> {
                    if (Objects.equals(freq, winCombinationEntry.getValue().getCount())) {
                        registerWinCombination(bettingAmount, symbolName, winCombinationEntry.getKey());
                    }
                }));

        // checking for linear symbols combinations
        config.getWinCombinations().entrySet().stream()
                .filter((entry) -> entry.getValue().getWhen() == When.LINEAR_SYMBOLS)
                .forEach(winCombinationEntry -> {
                    final WinCombination winCombination = winCombinationEntry.getValue();

                    for (List<Coordinate> coordinates : winCombination.getCoveredAreas()) {
                        SymbolName prevSymbol = null;
                        boolean hit = true;

                        for (Coordinate coordinate : coordinates) {
                            if (prevSymbol != null && prevSymbol != matrix[coordinate.getX()][coordinate.getY()]) {
                                hit = false;
                                break;
                            } else {
                                prevSymbol = matrix[coordinate.getX()][coordinate.getY()];
                            }
                        }

                        if (hit) {
                            registerWinCombination(bettingAmount, prevSymbol, winCombinationEntry.getKey());
                        }
                    }
                });

        final AtomicReference<SymbolName> bonusSymbol = new AtomicReference<>();

        if (!appliedWinningCombinations.isEmpty()) {
            frequencyMap.entrySet().stream().filter((entry) -> config.getSymbols().get(entry.getKey()).getType() == SymbolType.BONUS).forEach((entry) -> {
                if (entry.getKey() != SymbolName.MISS) {
                    BigDecimal reward = rewardAtomic.get();
                    Symbol bonus = config.getSymbols().get(entry.getKey());

                    if (bonus.getExtra() != null && bonus.getExtra().compareTo(BigDecimal.ZERO) > 0) {
                        reward = reward.add(bonus.getExtra());
                    } else if (bonus.getRewardMultiplier() != null && bonus.getRewardMultiplier().compareTo(BigDecimal.ZERO) > 0) {
                        reward = reward.multiply(bonus.getRewardMultiplier());
                    }

                    rewardAtomic.set(reward);
                    bonusSymbol.set(entry.getKey());
                } else {
                    rewardAtomic.set(BigDecimal.ZERO);
                    appliedWinningCombinations.clear();
                    bonusSymbol.set(SymbolName.MISS);
                }
            });
        }

        result.setMatrix(matrix);
        result.setReward(rewardAtomic.get());
        result.setAppliedWinningCombinations(appliedWinningCombinations);
        result.setAppliedBonusSymbol(bonusSymbol.get());

        return result;
    }

    private void registerWinCombination(final BigDecimal bettingAmount, final SymbolName symbolName, final WinCombinationName winCombinationName) {
        final List<WinCombinationName> combinations = appliedWinningCombinations.getOrDefault(symbolName, new ArrayList<>());
        combinations.add(winCombinationName);
        appliedWinningCombinations.put(symbolName, combinations);

        BigDecimal reward = rewardAtomic.get();
        reward = reward.add(bettingAmount.multiply(config.getSymbols().get(symbolName).getRewardMultiplier()).multiply(config.getWinCombinations().get(winCombinationName).getRewardMultiplier()));

        rewardAtomic.set(reward);
    }
}
