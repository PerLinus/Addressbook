package com.linus.nilsson83gmail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Linus Nilsson
 * 2016-11-29
 */

public class Serializer {

    private static final Logger log = Logger.getLogger(Serializer.class.getName());
    private File file = new File("AddressBook");

    public synchronized void writeToFile(List<Contact> list) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Something wrong occurred", e);
        }
    }

    public List readFromFile() {
        List<Contact> addressBook = null;
        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                addressBook = (List<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                log.log(Level.SEVERE, "Something wrong occurred", e);
                return addressBook;
            }
            return addressBook;
        }
        return addressBook;
    }
}
