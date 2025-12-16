package com.mtcoding.ex04;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.99", 20000);

            //읽기 버퍼
            InputStream keyStream = System.in;
            InputStreamReader keyReader = new InputStreamReader(keyStream);
            BufferedReader keyBuffer = new BufferedReader(keyReader);
            //쓰기 버퍼
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(ow);

            bw.write("소켓 접속이 완료됐습니다.\n");
            bw.flush();

            while(true){
                //키보드에서 입력받기
                String keyboardData = keyBuffer.readLine();
                //키보드 입력받은 데이터를 버퍼에 저장
                bw.write(keyboardData);
                bw.write("\n");
                bw.flush();
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