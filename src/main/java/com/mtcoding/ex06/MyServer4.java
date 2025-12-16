package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class MyServer4 {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();

            // 읽기 버퍼 - Client의 쓰기 버퍼에 있는 정보 가져오기
            Scanner scIn = new Scanner(socket.getInputStream());
            System.out.println("서버가 오픈되었습니다.");

            //쓰기 버퍼 - Client의 읽기 버퍼에 정보 보내기(응답 보내기)
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String line = scIn.nextLine();
            //외부 라이브러리 사용
            Gson gson = new Gson();
            //입력받은 문자열 값 Person 클래스로 다시 변환
            Person p = gson.fromJson(line, Person.class);
            System.out.println(p.getNo());
            System.out.println(p.getName());
            System.out.println(p.getAge());
            System.out.println(p.getHobby());
            pw.println("OK");

        }catch(RuntimeException e){
            //예외가 생긴 원인을 출력함
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("클라이언트 접속이 끊어졌습니다.");
        }
    }
}