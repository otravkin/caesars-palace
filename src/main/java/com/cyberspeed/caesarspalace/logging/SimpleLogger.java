package com.cyberspeed.caesarspalace.logging;

public class SimpleLogger {
    private static final String INFO_SEVERITY = "INFO";
    private static final String ERROR_SEVERITY = "ERROR";

    private final String className;

    private SimpleLogger(final String className) {
        this.className = className;
    }

    public static SimpleLogger getLogger(final Class<?> clazz) {
        return new SimpleLogger(clazz.getName());
    }

    public void info(final String message) {
        log(INFO_SEVERITY, message);
    }

    public void error(final String message) {
        log(ERROR_SEVERITY, message);
    }

    private void log(final String severity, final String message) {
        System.out.printf("[%s] %s: %s\n", severity, className, message);
    }
}
