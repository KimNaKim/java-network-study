package com.mtcoding.ex04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MyServer2 {
    public static void main(String[] args) throws IOException {
        try{
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();

            // 읽기 버퍼
            InputStream in = socket.getInputStream();   //읽기 버퍼에 소켓을 연결
            InputStreamReader ir = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir);
            System.out.println("[server] : 서버가 오픈되었습니다.");

            while(true){
                String line = br.readLine();            //엔터키까지 읽기
                System.out.println("[server] : " + line);
            }

        }catch(RuntimeException e){
            //예외가 생긴 원인을 출력함
            e.printStackTrace();
        }catch(SocketException e){
            System.out.println("클라이언트 접속이 끊어졌습니다.");
        }
    }
}