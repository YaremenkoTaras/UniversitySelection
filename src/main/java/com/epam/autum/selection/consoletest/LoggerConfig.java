package com.epam.autum.selection.consoletest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Tapac on 12.01.2017.
 */
public class LoggerConfig {

    private static Logger log = LogManager.getLogger(LoggerConfig.class);

    public static void main(String[] args) {
        log.info("Hello");
        log.error("Bad day");
        log.debug("Debug");
    }

}
