package com.med.jserver;

import java.net.*;
import java.io.*;

public class ConnectionThread implements Runnable {

    private Server server;
    private Socket clientSocket;
    private DataInputStream cin;

    public ConnectionThread(Server s, Socket c) {
        try {
            this.server = s;
            this.clientSocket = c;
            this.cin = new DataInputStream(c.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (true) {
            try {
                server.broadcast(this.clientSocket, cin.readUTF());
            } catch (IOException e) {
                System.out.println(clientSocket.getRemoteSocketAddress() + "处的远程客户端终止连接。");
                server.logout(this.clientSocket);
                break;
            }
        }
    }

}
