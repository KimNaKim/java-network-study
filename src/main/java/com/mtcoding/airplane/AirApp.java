package com.mtcoding.airplane;

import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class AirApp {
    //key값에 공항 이름, value에 공항ID 집어넣기
    static Map<String, String> ports = new HashMap<>();

    public static void main(String[] args) {
        try {
            // 1. 공항 정보 다운로드
            URL portURL = new URL("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&_type=json");
            HttpURLConnection conn = (HttpURLConnection) portURL.openConnection();
            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());

            String json = "";
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                json = json + line;
            }

            // 2. PortInfo로 오브젝트화 시키기
            Gson gson = new Gson();
            AirportInfo pInfo = gson.fromJson(json, AirportInfo.class);

            // 3. ports에 airportId에 있는 값, airportNm에 있는 값을 넣기
            List<AirportInfo.Response.Body.Items.Item> portItemList
                    = pInfo.getResponse().getBody().getItems().getItem();
            for(int i = 0; i<portItemList.size(); i++){
                ports.put(portItemList.get(i).getAirportNm(), portItemList.get(i).getAirportId());
            }


            Scanner keyboard = new Scanner(System.in);
            String dep = "";
            String depKey = "";
            String arr = "";
            String arrKey = "";
            String depTime = "";

            // 4. Scanner로 출발지를 변수에 넣기
            System.out.print("출발지를 입력해주세요 : ");
            dep = keyboard.nextLine();
            depKey = ports.get(dep);

            // 5. Scanner로 목적지 받기
            System.out.print("목적지를 입력해주세요 : ");
            arr = keyboard.nextLine();
            arrKey = ports.get(arr);

            // 6. Scanner로 날짜 받기
            System.out.print("출발시간을 입력해주세요 : ");
            depTime = keyboard.nextLine();
            
            // 7. 동적인 url 만들기
            String url = """
                    https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&pageNo=1&numOfRows=10&_type=json&depAirportId=${depKey}&arrAirportId=${arrKey}&depPlandTime=${depTime}&airlineId=KAL"""
                    .replace("${depKey}", depKey)
                    .replace("${arrKey}", arrKey)
                    .replace("${depTime}", depTime);

            //8. 항공스케줄 호출 - HttpUrlConnection사용
            URL scheURL = new URL(url);
            HttpURLConnection conn2 = (HttpURLConnection) scheURL.openConnection();
            conn2.setRequestMethod("GET");

            sc = new Scanner(conn2.getInputStream());

            json = "";
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                json = json + line;
            }

            //9. AirInfo로 파싱
            AirInfo aInfo = gson.fromJson(json, AirInfo.class);
            System.out.println(aInfo.toString());
            List<AirInfo.Response.Body.Items.Item> airItemList
                    = aInfo.getResponse().getBody().getItems().getItem();

            //10. 항공스케쥴 전체출력
            for(int i = 0; i<airItemList.size(); i++){
                System.out.println("항공사 이름 : " + airItemList.get(i).getAirlineNm());
                System.out.println("출발 공항 : " + airItemList.get(i).getDepAirportNm());
                System.out.println("도착 공항 : " + airItemList.get(i).getArrAirportNm());
                System.out.println("출발 시각 : " + airItemList.get(i).getDepPlandTime());
                System.out.println("항공기 ID : " + airItemList.get(i).getVihicleId());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}