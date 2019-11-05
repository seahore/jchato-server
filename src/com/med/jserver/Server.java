package com.med.jserver;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private ServerSocket ss;

    public Server(int port) throws IOException {
        ss = new ServerSocket(port);
        ss.setSoTimeout(5000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("在本地" + ss.getLocalPort() + "端口处开启服务器socket，等待远程连接……");
                Socket server = ss.accept();
                System.out.println("连接远程主机，地址为" + server.getRemoteSocketAddress());
                DataInputStream din = new DataInputStream(server.getInputStream());
                System.out.println(din.readUTF());
                DataOutputStream dout = new DataOutputStream(server.getOutputStream());
                dout.writeUTF("关闭连接了！" + server.getLocalSocketAddress());
                server.close();
            } catch (SocketTimeoutException se) {
                System.out.println("socket连接超时！");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
