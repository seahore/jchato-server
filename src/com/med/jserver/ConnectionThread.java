package com.med.jserver;

import java.net.*;
import java.io.*;

public class ConnectionThread implements Runnable {

    private Server server;
    private Socket client;
    private DataInputStream cin;

    public ConnectionThread(Server s, Socket c) {
        try {
            this.server = s;
            this.client = c;
            this.cin = new DataInputStream(c.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (true) {
            try {
                server.broadcast(this.client, cin.readUTF());
            } catch (IOException e) {
                System.out.println(client.getRemoteSocketAddress() + "处的远程客户端终止连接。");
                server.logout(this.client);
                break;
            }
        }
    }

}
