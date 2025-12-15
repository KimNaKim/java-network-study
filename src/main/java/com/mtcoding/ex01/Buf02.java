package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf02 {
    public static void main(String[] args) {
        //1. 바이트 스트림 연결
        InputStream in = System.in;     //키보드를 연결
        
        //불편한 점 1. 글자 하나밖에 입력하지 못함
        //불편한 점 2. 문자를 입력해도 숫자로 변환됨(캐스팅을 해주지 않음)
        //해결방법 : while문을 사용하거나, 배열을 사용하기(버퍼), 문자로 다시 캐스팅하기
        try{
            //2. 배열을 가질 수 있고, 문자로 캐스팅해줌
            InputStreamReader ir = new InputStreamReader(in);
            char[] buf = new char[3];
            //입력된 byte 정보를 char로 변환해서 버퍼에 저장함
            ir.read(buf);           //주소를 넘겼음, \n 입력 대기중

            for(char c : buf){
                System.out.print(c + ", ");
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
