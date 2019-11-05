package com.med.jserver;

import java.io.IOException;

public class JServer {
    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("请输入端口号参数。");
            return;
        }
        try {
            Thread t = new Server(port);
            t.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
