package com.linus.nilsson83gmail;


import java.util.*;
import java.util.logging.Logger;

/**
 * Linus Nilsson
 * 2016-11-29
 */

class AddressBook {

    private static final Logger log = Logger.getLogger(AddressBook.class.getName());
    private List<Contact> contactList = new ArrayList<>();
    private List<Contact> serverContactList = new ArrayList<>();
    Serializer serializer = new Serializer();

    public AddressBook() {
        log.info("Program started");
        contactList = serializer.readFromFile();
        log.info("Saved contacts was loaded");
        if (contactList == null) {
            contactList = new ArrayList<>();
        }
    }

    public void addContact(String firstName, String lastName, String emailAddress) {
        Contact contact = new Contact(firstName, lastName, emailAddress);
        contactList.add(contact);
        System.out.println("Contact was added.");
    }

    public List<Contact> searchForContact(String searchParam) {
        List<Contact> searchList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getFirstName().toLowerCase().startsWith(searchParam.toLowerCase()) ||
                    contact.getLastName().toLowerCase().startsWith(searchParam.toLowerCase())) {
                searchList.add(contact);
            }
        }
        for (Contact serverContact : serverContactList) {
            if (serverContact.getFirstName().toLowerCase().startsWith(searchParam.toLowerCase()) ||
                    serverContact.getLastName().toLowerCase().startsWith(searchParam.toLowerCase())) {
                searchList.add(serverContact);
            }
        }
        return searchList;
    }

    public void deleteContact(String contactID) {
        List<Contact> deleteList = new ArrayList<>();
        for (int i = serverContactList.size() - 1; i > -1; i--) {
            if (serverContactList.get(i).getContactID().equals(contactID)) {
                System.out.println("Database contacts cannot be deleted.");
                break;
            }
        }
        for (int i = contactList.size() - 1; i > -1; i--) {
            if (contactList.get(i).getContactID().equals(contactID)) {
                deleteList.add(contactList.get(i));
                System.out.println(contactList.get(i).getContactID() + " was deleted.");
                contactList.remove(contactList.get(i));
            }
        }
        if (deleteList.isEmpty()) {
            System.out.println("Could not find the contact in local contactlist.");
        }
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public List<Contact> getServerContactList() {
        return serverContactList;
    }

    public void setServerContactList(List<Contact> serverContactList) {
        this.serverContactList = serverContactList;
    }

}


