package com.linus.nilsson83gmail;

/**
 * Created by Linus Nilsson on 2016-12-01.
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerClient implements Runnable {

    private String ip_address;
    private int port;

    private static final Logger log = Logger.getLogger(ServerClient.class.getName());
    private List<Contact> serverContactList = new ArrayList<>();

    public ServerClient(String ip_address, int port){
        this.ip_address = ip_address;
        this.port = port;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(40_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (Socket socket = new Socket(ip_address, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            log.info("Connection with server was establisht");
            writer.println("getall");
            writer.flush();


            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.equals("")) {
                    break;
                }
                String[] contacts = line.split(" ");
                Contact contact = new Contact(contacts[0], contacts[1], contacts[2], contacts[3]);
                serverContactList.add(contact);
            }
            log.info("Contacts loaded from server");
            writer.println("exit");
            writer.flush();
            log.info("ServerClient disconnected from server");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Something wrong occurred", e);
            System.out.println("Could not retrieve server database contacts.");
        }
    }

    public List<Contact> readServerContactList() {

        return serverContactList;
    }
}
