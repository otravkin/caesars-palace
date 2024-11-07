package com.cyberspeed.caesarspalace.game;

import com.cyberspeed.caesarspalace.domain.Config;
import com.cyberspeed.caesarspalace.domain.Probability;
import com.cyberspeed.caesarspalace.domain.SymbolName;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MatrixInitializer {

    private static final double BONUS_PROBABILITY = 0.15;

    final Random random = new Random();

    public SymbolName[][] initMatrix(final Config config) {
        final SymbolName[][] matrix = new SymbolName[config.getColumns()][config.getRows()];
        boolean bonusHit = false; // just one bonus symbol per matrix allowed

        for (int i = 0; i < config.getColumns(); i++) {
            for (int j = 0; j < config.getRows(); j++) {
                final Probability standardProbability = config.getProbabilities().getStandardSymbols().get(config.getColumns() * i + j);
                final Probability bonusProbability = config.getProbabilities().getBonusSymbols();

                SymbolName selectedSymbol;

                if (!bonusHit && random.nextDouble(1.0) <= BONUS_PROBABILITY) {
                    selectedSymbol = chooseSymbol(bonusProbability);
                    bonusHit = true;
                } else {
                    selectedSymbol = chooseSymbol(standardProbability);
                }

                matrix[i][j] = selectedSymbol;
            }
        }

        return matrix;
    }

    private SymbolName chooseSymbol(final Probability probability) {
        final int totalWeight = probability.getSymbols().values().stream().mapToInt(Integer::intValue).sum();
        final Map<SymbolName, Integer> cumulativeWeights = new TreeMap<>();
        Integer cumulativeSum = 0;

        for (Map.Entry<SymbolName, Integer> entry : probability.getSymbols().entrySet()) {
            cumulativeSum += entry.getValue();
            cumulativeWeights.put(entry.getKey(), cumulativeSum);
        }

        int randomValue = random.nextInt(totalWeight) + 1;

        SymbolName selectedSymbol = SymbolName.A;
        for (Map.Entry<SymbolName, Integer> entry : cumulativeWeights.entrySet()) {
            if (randomValue <= entry.getValue()) {
                selectedSymbol = entry.getKey();
                break;
            }
        }

        return selectedSymbol;
    }
}
