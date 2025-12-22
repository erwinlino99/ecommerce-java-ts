package com.ecommerce.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseLogger {

    private static final Logger log = LoggerFactory.getLogger(UseLogger.class);

    private static final String BLUE = "\u001B[34m";
    private static final String ORANGE = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

public static void info(String message, Object data) {
    log.info(
        "\n\n" +
        BLUE + "--- {} : {} ---" + RESET +
        "\n\n",
        message,
        data
    );
}

public static void warning(String message, Object data) {
    log.warn(
        "\n\n" +
        ORANGE + "--- {} : {} ---" + RESET +
        "\n\n",
        message,
        data
    );
}

public static void error(String message, Object data) {
    log.error(
        "\n\n" +
        RED + "--- {} : {} ---" + RESET +
        "\n\n",
        message,
        data
    );
}

}
