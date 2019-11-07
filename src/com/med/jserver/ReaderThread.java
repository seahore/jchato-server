package com.med.jserver;

import java.io.*;
import java.util.Scanner;

public class ReaderThread implements Runnable {
    private Scanner inputScanner;
    private Server receiver;

    public ReaderThread(InputStream is, Server rcv) {
        this.inputScanner = new Scanner(is);
        this.receiver = rcv;
    }

    public void run() {
        while (true) {
            receiver.broadcast("服务器：" + inputScanner.next());
        }
    }
}
