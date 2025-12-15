package com.mtcoding.ex02;

import java.io.*;

public class File02 {
    public static void main(String[] args) {
        //읽기
        FileReader fr = null;
        try {
           /* out = new FileOutputStream("fileName.txt");
            OutputStreamWriter ow = new OutputStreamWriter(out);*/
            //파일에 바이트 스트림 연결
            fr = new FileReader("socket.txt");
            BufferedReader br = new BufferedReader(fr);
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
