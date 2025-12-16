package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class MyClient4 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.88", 20000);

            //읽기 버퍼 - Server의 쓰기 버퍼에 저장된 정보 가져오기(응답 받기)
            Scanner socketSc = new Scanner(socket.getInputStream());
            //쓰기 버퍼 - Server의 읽기 버퍼에 보낼 정보 저장하기
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //자동으로 flush 됨

            Person person = new Person(1,"홍길동",20, Arrays.asList("축구","농구"));
            //외부 라이브러리 사용
            Gson gson = new Gson();
            String json = gson.toJson(person);

            pw.println(json);
            String reev = socketSc.nextLine();
            //전송된 Person 객체를 String타입에 딸린 함수로 파싱

            System.out.println("[For Server] : " + reev);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}