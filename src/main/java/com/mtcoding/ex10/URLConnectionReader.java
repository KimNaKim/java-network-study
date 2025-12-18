package com.mtcoding.ex10;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLConnectionReader {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://search.naver.com/" +
                    "search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&" +
                    "query=%EB%82%A0%EC%94%A8&ackey=n5kxofuv");    //DNS로 IP를 찾아주는 클래스
            //포트번호와 IP를 몰라도 접속할 수 있음
            //스트림 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //읽기 버퍼 생성
            Scanner sc = new Scanner(conn.getInputStream());
            String html = "";
            while(sc.hasNextLine()){
               String line = sc.nextLine();
               html = html + line;
            }
            System.out.println(html);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
