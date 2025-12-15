package com.mtcoding.ex03;

import java.io.IOException;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("LocalHost", 20000);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //루프백 주소 : 로컬 호스트

    }
}
