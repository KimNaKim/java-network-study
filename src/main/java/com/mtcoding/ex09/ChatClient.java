package com.mtcoding.ex09;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try{
            //소켓 작성
            Socket socket = new Socket("192.168.0.99", 10000);
            
            //버퍼작성- 키보드, 쓰기버퍼, 읽기버퍼
            PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
            Scanner receiver = new Scanner(socket.getInputStream());
            Scanner keyboard = new Scanner(System.in);

            //1. 송신 스레드
            new Thread(() -> {
                while(true){
                    String msg = keyboard.nextLine();
                    sender.println(msg);
                }
            }).start();
            //2. 읽기 스레드
            new Thread(() -> {
                while(true){
                    String msg = receiver.nextLine();
                    System.out.println("[message] : " + msg);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
