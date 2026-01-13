package com.ecommerce.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UseLogger {

    private static final Logger log = LoggerFactory.getLogger(UseLogger.class);

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT); // Para que el JSON salga con saltos de línea

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
                data);
    }

    public static void warning(String message, Object data) {
        log.warn(
                "\n\n" +
                        ORANGE + "--- {} : {} ---" + RESET +
                        "\n\n",
                message,
                data);
    }

    public static void error(String message, Object data) {
        log.error(
                "\n\n" +
                        RED + "--- {} : {} ---" + RESET +
                        "\n\n",
                message,
                data);
    }

    public static void logAsJson(String title, Object data) {
        try {
            String json = mapper.writeValueAsString(data);
            log.info("\n{}{}:\n{}{}", BLUE, title, json, RESET);
        } catch (Exception e) {
            log.error("Error al serializar a JSON: {}", e.getMessage());
        }
    }

}
