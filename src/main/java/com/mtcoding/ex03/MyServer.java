package com.mtcoding.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) {
        try {
            //1. 리스너
            ServerSocket ss = new ServerSocket(20000);

            //2. 소켓
            Socket socket = new Socket();

            //3. 버퍼
            InputStream in = socket.getInputStream();
            InputStreamReader ir = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir);

            //
            String msg = br.readLine();
            System.out.println("클라이언트 메시지 : " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
