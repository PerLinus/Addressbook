package com.linus.nilsson83gmail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Linus Nilsson
 * 2016-11-29
 */
public class OutputFormatter {

    public void printContactList(AddressBook addressBook) {
        if (addressBook.getContactList().isEmpty() && addressBook.getServerContactList().isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            print(sortContacts(addressBook.getContactList(), addressBook.getServerContactList()));
        }
    }

    public void printSearchList(List<Contact> searchList) {
        if (searchList.isEmpty()) {
            System.out.println("Could not find the contact.");
        } else {
            print(sortContacts(searchList));
        }
    }

    public List<Contact> sortContacts(List<Contact> contactList) {
        List<Contact> sortedContacts = new ArrayList<>(contactList);
        Collections.sort(sortedContacts);

        return sortedContacts;
    }

    public List<Contact> sortContacts(List<Contact> contactList1, List<Contact> contactList2) {
        List<Contact> sortedContacts = new ArrayList<>(contactList1);
        sortedContacts.addAll(contactList2);
        Collections.sort(sortedContacts);

        return sortedContacts;
    }

    public void print(List<Contact> printList) {
        for (Contact contact : printList) {
            System.out.printf("ID: %s \nFirstname: %s \nLastname: %s \nEmailaddress: %s\n\n", contact.getContactID(),
                    contact.getFirstName(), contact.getLastName(), contact.getEmailAddress());
        }
    }

    public void helpList() {
        System.out.printf("%-10s %s\n%-10s %s\n%-10s %s\n%-10s %s\n%-10s %s\n%-10s %s\n",
                "quit", "quits the program.", "add", "adds a new contact, add firstname lastname email.",
                "list", "lists all contacts.", "search", "search for a contact, by starting letters in firstname or lastname.",
                "delete", "deletes a contact, use contact ID.", "help", "shows help meny.");
    }

    public void invalidCommand(String command) {
        System.out.println("invalid command: " + command + ". Type 'help' for commands.");
    }

    public void invalidCommand() {
        System.out.println("Invalid command. Type 'help' for commands.");
    }

}
