package com.med.jserver;

import java.io.IOException;

public class JServer {
    public static void main(String[] args) {
        int port, timeout;

        try {
            if (args.length >= 1)
                port = Integer.parseInt(args[0]);
            else
                port = 666;

            if (args.length >= 2)
                timeout = Integer.parseInt(args[1]);
            else
                timeout = 0;
        } catch (NumberFormatException e) {
            System.out.println("请输入正确格式的参数。");
            return;
        }

        try {
            Thread t = new Server(port, timeout);
            t.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
