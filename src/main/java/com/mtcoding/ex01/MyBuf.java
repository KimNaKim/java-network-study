package com.mtcoding.ex01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyBuf {
    BufferedReader br;

    public void MyBuf(InputStream in){
        in = System.in;
        InputStreamReader ir = new InputStreamReader(in);
        this.br = new BufferedReader(ir);
    }
}
