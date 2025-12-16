package com.mtcoding.ex05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MyServer3 {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();

            // 읽기 버퍼 - Client의 쓰기 버퍼에 있는 정보 가져오기
            BufferedReader br1 = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println("서버가 오픈되었습니다.");

            //쓰기 버퍼 - Client의 읽기 버퍼에 정보 보내기(응답 보내기)
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);


            while(true){
                String line = br1.readLine();            //엔터키까지 읽기
                System.out.println("[To server] : " + line);
                pw.println("OK");
            }




        }catch(RuntimeException e){
            //예외가 생긴 원인을 출력함
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("클라이언트 접속이 끊어졌습니다.");
        }
    }
}