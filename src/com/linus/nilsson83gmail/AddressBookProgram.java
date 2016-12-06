package com.linus.nilsson83gmail;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Linus Nilsson
 * 2016-11-29
 */

public class AddressBookProgram {

    private static final Logger log = Logger.getLogger(AddressBookProgram.class.getName());

    private AddressBook addressBook = new AddressBook();
    private ServerClient serverClient = new ServerClient("localhost", 61616);
    private SaveHandler saveHandler = new SaveHandler();
    private OutputFormatter outputFormatter = new OutputFormatter();
    private Scanner scanner = new Scanner(System.in);

    public void run() {

        System.out.println("Welcome!");
        new Thread(() -> {
        serverClient.run();
        }).start();
        addressBook.setServerContactList(serverClient.readServerContactList());
        saveHandler.autoSave(addressBook);
        String userInput;
        String[] splitUserInput;

        while (true) {
            try {
                userInput = scanner.nextLine();
                splitUserInput = userInput.split(" ");
                log.fine("User input: " + userInput);

                switch (splitUserInput[0]) {
                    case "quit":
                        if (splitUserInput.length == 1) {
                            quit(addressBook);
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    case "add":
                        if (splitUserInput.length == 4) {
                            addressBook.addContact(splitUserInput[1], splitUserInput[2], splitUserInput[3]);
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    case "list":
                        if (splitUserInput.length == 1) {
                            outputFormatter.printContactList(addressBook);
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    case "search":
                        if (splitUserInput.length == 2) {
                            outputFormatter.printSearchList(addressBook.searchForContact(splitUserInput[1]));
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    case "delete":
                        if (splitUserInput.length == 2) {
                            addressBook.deleteContact(splitUserInput[1]);
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    case "help":
                        if (splitUserInput.length == 1) {
                            outputFormatter.helpList();
                        } else outputFormatter.invalidCommand(userInput);
                        break;

                    default:
                        outputFormatter.invalidCommand(userInput);
                        break;

                } // END OF SWITCH
            } catch (ArrayIndexOutOfBoundsException e) {
                log.log(Level.SEVERE, "Something wrong occurred", e);
                outputFormatter.invalidCommand();
            }
        } // END OF WHILE-LOOP
    }

    public void quit(AddressBook addressBook) {
        saveHandler.saveAddressBook(addressBook);
        System.out.println("Welcome back!");
        log.info("Program ended and contacts was saved");
        System.exit(0);
    }
}
