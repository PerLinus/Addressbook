package com.linus.nilsson83gmail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Linus Nilsson
 * 2016-11-29
 */

public class Main {

    public static void main(String[] args) {

        setupLogging();
        AddressBookProgram addressBookProgram = new AddressBookProgram();
        addressBookProgram.run();

    }

    public static void setupLogging() {

        String LoggingFilePath = "C:\\Java-Nackademin\\Projekt OOP\\Address book\\src\\logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(LoggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties.", e);
        }
    }
}


