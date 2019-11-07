package com.med.jserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets;

    public Server(int port) throws IOException, BindException {
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSockets = new ArrayList<Socket>();
        } catch (BindException be) {
            throw be;
        }
        serverSocket.setSoTimeout(0);
    }

    protected synchronized void broadcast(String contents) {
        try {
            contents = contents;
            System.out.println(contents);
            for (Socket c : clientSockets) {
                new DataOutputStream(c.getOutputStream()).writeUTF(contents);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    protected synchronized void broadcast(Socket sender, String contents) {
        try {
            contents = sender.getRemoteSocketAddress() + ": " + contents;
            System.out.println(contents);
            for (Socket c : clientSockets) {
                new DataOutputStream(c.getOutputStream()).writeUTF(contents);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void logout(Socket c) {
        clientSockets.remove(c);
    }

    public void run() {
        System.out.println("在本地" + serverSocket.getLocalPort() + "端口处开启服务器socket，等待远程连接……");
        new Thread(new ReaderThread(System.in, this)).start();
        while (true) {
            try {
                Socket currentClientSocket = serverSocket.accept();
                System.out.println("远程主机客户端请求连接，地址为" + currentClientSocket.getRemoteSocketAddress());
                new Thread(new ConnectionThread(this, currentClientSocket)).start();
                clientSockets.add(currentClientSocket);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
