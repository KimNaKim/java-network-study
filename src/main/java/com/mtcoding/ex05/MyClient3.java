package com.mtcoding.ex05;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.88", 20000);

            //읽기 버퍼 - Server의 쓰기 버퍼에 저장된 정보 가져오기(응답 받기)
            BufferedReader br1 = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            //쓰기 버퍼 - Server의 읽기 버퍼에 보낼 정보 저장하기
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //자동으로 flush 됨

            while(true){
                //키보드에서 입력받기
                String keyboardData = sc.nextLine();
                //키보드 입력받은 데이터를 버퍼에 저장, 출력
                pw.println(keyboardData);

                //전송 상황 읽어오기
                String msg = br1.readLine();
                System.out.println("[For server] : " + msg);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}