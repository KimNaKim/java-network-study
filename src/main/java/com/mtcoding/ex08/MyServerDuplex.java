package com.mtcoding.ex08;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServerDuplex {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(20000);
            System.out.println("[server] 대기중...");

            Socket socket = ss.accept();
            System.out.println("[server] 연결됨: " + socket.getInetAddress());

            //읽기 버퍼
            Scanner socketSc = new Scanner(socket.getInputStream());
            //쓰기 버퍼
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //키보드 정보를 입력받는 버퍼
            Scanner keyboardSc = new Scanner(System.in);

            // 1) 수신 스레드: 클라이언트 -> 서버
            //읽기 버퍼에 연결됨
            //상태와 행위를 같이 전달하고 싶을 경우 클래스를 사용
            Thread receiver = new Thread(() -> {
                try {
                    while (socketSc.hasNextLine()) {
                        String line = socketSc.nextLine();
                        System.out.println("[server][recv] " + line);

                        // 자동 응답도 가능
                        // pw.println("ok: " + line);

                        if ("quit".equalsIgnoreCase(line)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[server] receiver 종료: " + e.getMessage());
                }
            });

            // 2) 송신 스레드: 서버 -> 클라이언트 (서버 키보드 입력)
            //쓰기 버퍼에 연결됨
            Thread sender = new Thread(() -> {
                try {
                    while (true) {
                        String msg = keyboardSc.nextLine();
                        pw.println(msg);

                        if ("quit".equalsIgnoreCase(msg)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[server] sender 종료: " + e.getMessage());
                }
            });

            receiver.start();
            sender.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}