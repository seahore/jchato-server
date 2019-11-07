package com.med.jserver;

import java.io.IOException;
import java.net.BindException;

public class Main {
    public static void main(String[] args) {
        int port;

        try {
            if (args.length >= 1)
                port = Integer.parseInt(args[0]);
            else
                port = 666;
        } catch (NumberFormatException e) {
            System.out.println("请输入正确格式的参数。");
            return;
        }

        try {
            Server t = new Server(port);
            t.run();
        } catch (BindException be) {
            System.out.println(port + "端口已被占用，请重新设置。");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
