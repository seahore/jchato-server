package com.med.jserver;

import java.net.*;
import java.io.*;

public class ConnectionThread implements Runnable {

    private Socket client;
    DataInputStream din;
    DataOutputStream dout;

    public ConnectionThread(Socket c) {
        try {
            this.client = c;
            this.din = new DataInputStream(c.getInputStream());
            this.dout = new DataOutputStream(c.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (true) {
            try {
                System.out.println(din.readUTF());
            } catch (IOException e) {
                System.out.println(client.getRemoteSocketAddress() + "处的远程客户端终止连接。");
                break;
            }
        }
    }

}
