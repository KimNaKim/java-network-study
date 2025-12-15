package com.mtcoding.ex01;

import java.io.*;

public class Buf04 {
    public static void main(String[] args) {
        //Write(Output) 스트림 연결, 기능확장
        OutputStream out = System.out;
        OutputStreamWriter or = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(or);
        
        try {
            bw.write("ABC");    //버퍼에 입력값을 집어넣기
            bw.flush();            //버퍼를 비우기
            //버퍼가 꽉 찼을 때에는 자동으로 비워짐

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
