package com.med.jserver;

import java.net.*;
import java.io.*;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private int clientCount;
    private int idleTimeout;

    public Server(int port) throws IOException, BindException {
        this.clientCount = 0;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (BindException be) {
            throw be;
        }
        serverSocket.setSoTimeout(0);
    }

    public void run() {
        System.out.println("在本地" + serverSocket.getLocalPort() + "端口处开启服务器socket，等待远程连接……");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ++clientCount;
                System.out.println("远程主机客户端请求连接，地址为" + client.getRemoteSocketAddress());
                new Thread(new ConnectionThread(client)).start();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
