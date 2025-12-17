package com.mtcoding.ex09;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ClientThread implements Runnable{
     Socket socket;
     PrintWriter sender;
     Scanner receiver;

     public ClientThread(Socket socket, PrintWriter sender, Scanner receiver) {
         this.socket = socket;
         this.sender = sender;
         this.receiver = receiver;
     }

     //runnable 인터페이스를 상속받았기 때문에 override 필수
    @Override
    public void run() {
        //새로운 스레드가 생성되고, 각자의 스레드 위치에서 대기중
        //읽기 버퍼(리시버)를 이용해 메시지 받기
        //여러 번 받아야하므로 while문 사용하기
        while(true){
            String msg = receiver.nextLine();

            //받은 메시지를 센더로 전송
            for(ClientThread t : ChatServer.boxes){
                //boxes 리스트에서 자기자신에겐 메시지 전송할 필요 x
                if(t != this){
                    t.sender.println(msg);
                }
            }
        }
    }
 }


public class ChatServer {
    //소켓 객체 여러 개를 담을 리스트 생성
    static List<ClientThread> boxes = new ArrayList<>();

    public static void main(String[] args) {
        //초기화
        try{
            //서버소켓 & 소켓 작성
            ServerSocket ss = new ServerSocket(10000);
            while(true){
                //멤버 접속 시마다 소켓을 생성하기
                Socket socket = ss.accept();    //메인 스레드 대기

                //브로드캐스트는 읽기/쓰기 버퍼를 미리 생성해두는 것이 불가능
                //연결되는 소켓 수(인원수)에 맞게 버퍼를 생성하는 알고리즘이 필요
                //송신기/수신기 객체 생성
                PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                Scanner receiver = new Scanner(socket.getInputStream());

                //스레드 생성 (작업 단위에 스레드를 부여)
                ClientThread ct = new ClientThread(socket, sender, receiver);
                //객체 생성&리스트에 객체 삽입
                boxes.add(ct);
                new Thread(ct).start();
            }
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e.getMessage());
        }
    }
}
