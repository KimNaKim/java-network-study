package com.mtcoding.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf03 {
    public static void main(String[] args) {
        //기능 확장 -> 데코레이터(장식) 패턴
        //가변 길이의 버퍼를 사용하는 코드
        //1. 바이트 스트림 연결
        InputStream in = System.in;
        
        //2. 숫자를 문자로 변환해주는 객체 생성
        InputStreamReader ir = new InputStreamReader(in);

        //3. 가변 길이의 버퍼를 생성 (기존에 있는 클래스를 사용)
        BufferedReader br = new BufferedReader(ir);     //\n 기준으로 입력을 끊음

        try {
            //버퍼에 저장된 정보 출력
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
