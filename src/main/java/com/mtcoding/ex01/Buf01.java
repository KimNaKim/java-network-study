package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;

public class Buf01 {
    public static void main(String[] args) {
        //1. 키보드와 컴퓨터가 스트림이 연결됨
        InputStream in = System.in;     //바이트 스트림 연결

        //2. 바이트 읽기
        try{
            int n = in.read();      //키보드로부터 입력을 대기하는 상태 \n (엔터키 입력을 대기)
            System.out.println(n);
        }catch(IOException e){
            throw new RuntimeException(e);
        }


    }
}
