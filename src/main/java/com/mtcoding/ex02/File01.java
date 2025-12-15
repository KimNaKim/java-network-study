package com.mtcoding.ex02;

import java.io.*;

public class File01 {
    public static void main(String[] args) {
        //쓰기
        FileWriter fw = null;
        try {
           /* out = new FileOutputStream("fileName.txt");
            OutputStreamWriter ow = new OutputStreamWriter(out);*/
            fw = new FileWriter("socket.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("ABC");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
