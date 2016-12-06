package com.linus.nilsson83gmail;

import java.io.Serializable;
import java.util.UUID;

/**
 * Linus Nilsson
 * 2016-11-29
 */

class Contact implements Serializable, Comparable<Contact> {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactID;

    public Contact(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        contactID = UUID.randomUUID().toString();
    }

    public Contact(String ID, String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        contactID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactID() {
        return contactID;
    }

    @Override
    public int compareTo(Contact o) {
        return firstName.compareToIgnoreCase(o.getFirstName());
    }
}
