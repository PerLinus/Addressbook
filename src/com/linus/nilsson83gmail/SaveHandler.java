package com.linus.nilsson83gmail;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Linus Nilsson
 * 2016-11-29
 */
public class SaveHandler {

    private static final Logger log = Logger.getLogger(SaveHandler.class.getName());
    Serializer serializer = new Serializer();

    public void saveAddressBook(AddressBook addressBook) {
        serializer.writeToFile(addressBook.getContactList());
    }

    public void autoSave(AddressBook addressBook) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    log.log(Level.SEVERE, "Something wrong occurred", e);
                }
                saveAddressBook(addressBook);
                log.info("Contacts was autosaved.");
            }
        }).start();
    }
}


