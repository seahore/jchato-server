package com.med.jserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clients;

    public Server(int port) throws IOException, BindException {
        try {
            this.serverSocket = new ServerSocket(port);
            this.clients = new ArrayList<Socket>();
        } catch (BindException be) {
            throw be;
        }
        serverSocket.setSoTimeout(0);
    }

    protected void broadcast(Socket sender, String contents) {
        try {
            for (Socket c : clients) {
                new DataOutputStream(c.getOutputStream()).writeUTF(sender.getRemoteSocketAddress() + ": " + contents);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void run() {
        System.out.println("在本地" + serverSocket.getLocalPort() + "端口处开启服务器socket，等待远程连接……");
        while (true) {
            try {
                Socket currentClientSocket = serverSocket.accept();
                System.out.println("远程主机客户端请求连接，地址为" + currentClientSocket.getRemoteSocketAddress());
                new Thread(new ConnectionThread(this, currentClientSocket)).start();
                clients.add(currentClientSocket);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
