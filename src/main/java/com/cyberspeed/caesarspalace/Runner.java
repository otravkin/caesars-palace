package com.cyberspeed.caesarspalace;

import com.cyberspeed.caesarspalace.config.ConfigLoader;
import com.cyberspeed.caesarspalace.domain.Result;
import com.cyberspeed.caesarspalace.game.Game;
import com.cyberspeed.caesarspalace.logging.SimpleLogger;
import com.cyberspeed.caesarspalace.util.Args;
import com.cyberspeed.caesarspalace.util.ArgsExtractor;
import com.cyberspeed.caesarspalace.util.ResultPrinter;

public class Runner {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(Runner.class);

    public static void main(final String[] argz) {
        try {
            final Args args = ArgsExtractor.extractArgs(argz);
            final Game game = new Game(ConfigLoader.loadFromFile(args.getConfigPath()));

            final Result result = game.performSpin(args.getBettingAmount());

            ResultPrinter.printResult(result);

            LOG.info("File \"result.json\" was successfully generated.");
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }
}
