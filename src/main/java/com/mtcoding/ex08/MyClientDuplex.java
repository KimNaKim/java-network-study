package com.mtcoding.ex08;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClientDuplex {           //일대일-전이중 통신

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.88", 20000);

            Scanner keyboardSc = new Scanner(System.in);
            Scanner socketSc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // 1) 수신 스레드: 서버 -> 클라이언트
            Thread receiver = new Thread(() -> {
                try {
                    while (socketSc.hasNextLine()) {
                        String recv = socketSc.nextLine();
                        System.out.println("[client][recv] " + recv);
                    }
                } catch (Exception e) {
                    System.out.println("[client] receiver 종료: " + e.getMessage());
                }
            });

            // 2) 송신 스레드: 클라이언트 -> 서버 (키보드)
            Thread sender = new Thread(() -> {
                try {
                    while (true) {
                        String msg = keyboardSc.nextLine(); // 블로킹
                        pw.println(msg); // autoFlush=true

                        // 종료 명령 예시
                        if ("quit".equalsIgnoreCase(msg)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[client] sender 종료: " + e.getMessage());
                }
            });

            receiver.start();
            sender.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}