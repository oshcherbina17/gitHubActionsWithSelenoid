package org.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class Main {
    public static void main(String[] args) {
        final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

        LOGGER.info("Hello world!");
    }
}