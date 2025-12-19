package com.mtcoding.bus;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BusApp {
    static Map<String, String> stopNames = new HashMap<>();
    public static void main(String[] args) {
        try{
            //1.bstopNm으로 bstopId 알아내기
            //1-1. 정류장id-정류장번호
            //1-2. URL 생성해 정류소정보 조회하기
            Scanner keyboard = new Scanner(System.in);
            String stopName = "";
            String sURL = "";

            //정류소이름 입력받기
            System.out.print("승차할 정류소 이름을 입력하세요 : ");
            stopName = keyboard.nextLine();
            String encodedStopName = URLEncoder.encode(stopName, StandardCharsets.UTF_8);
            //동적URL(1)
            sURL = """
                    https://apis.data.go.kr/6260000/BusanBIMS/busStopList?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&pageNo=1&numOfRows=10&bstopnm=%s"""
                    .formatted(encodedStopName);
            URL stopURL = new URL(sURL);
            HttpURLConnection conn = (HttpURLConnection) stopURL.openConnection();
            conn.setRequestMethod("GET");

            // 1-3. jackson 외부 라이브러리 사용
            // 1-4. 오브젝트화 시키기
            XmlMapper xmlMapper = new XmlMapper();
            StopInfo sInfo = xmlMapper.readValue(stopURL, StopInfo.class);

            // 1-5. 오브젝트화 시킨 목록들 리스트에 넣기
            List<StopInfo.Body.Items.Item> stopItemList =
                    sInfo.getBody().getItems().getItem();
            // 1-6. 조회
            System.out.println("입력하신 이름과 일치하는 정류장 목록 : ");
            for(int i = 0; i<stopItemList.size(); i++){
                stopNames.put(stopItemList.get(i).getArsno(),stopItemList.get(i).getBstopnm());
                System.out.println("이름 : " + stopItemList.get(i).getBstopnm());
                System.out.println("정류소ID : " + stopItemList.get(i).getBstopid());
                System.out.println("정류소번호 : " + stopItemList.get(i).getArsno());
                System.out.println();
            }


            //2.노선 입력 받기
            String arsNo = "";

            System.out.print("정류소번호를 입력하세요 : ");
            arsNo = keyboard.nextLine();

            //2-2 동적 URL(2)
            String aURL = """
                    https://apis.data.go.kr/6260000/BusanBIMS/bitArrByArsno?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&arsno=${arsno}"""
                    .replace("${arsno}", arsNo);

            URL arrURL = new URL(aURL);
            HttpURLConnection conn2 = (HttpURLConnection) arrURL.openConnection();
            conn2.setRequestMethod("GET");

            // 2-3. jackson 외부 라이브러리 사용
            // 2-4. 오브젝트화 시키기
            ArrInfo aInfo = xmlMapper.readValue(arrURL, ArrInfo.class);
            List<ArrInfo.Body.Items.Item> arrItemList =
                    aInfo.getBody().getItems().getItem();

            //2-5. 조회
            System.out.println(stopNames.get(arsNo) + "정류소에 도착하는 버스 목록");
            for(int i = 0; i<arrItemList.size(); i++){
                System.out.println("노선번호 : " + arrItemList.get(i).getLineno());
                System.out.println("남은 시간 : " + arrItemList.get(i).getMin1());
                System.out.println("남은 정류장 개수 : " + arrItemList.get(i).getStation1());
                System.out.println("버스 타입 : " + arrItemList.get(i).getBustype());
                System.out.println();
            }

            //3. 버스번호로 노선 검색하기
            //버스번호 입력 받기
            String busNo = "";

            System.out.print("버스번호를 입력하세요 : ");
            busNo = keyboard.nextLine();

            //동적 URL(3)
            String bURL = """
                    https://apis.data.go.kr/6260000/BusanBIMS/busInfo?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&lineno=${busNo}"""
                    .replace("${busNo}", busNo);
            URL busURL = new URL(bURL);
            HttpURLConnection conn3 = (HttpURLConnection) busURL.openConnection();
            conn3.setRequestMethod("GET");

            //입력받은 값으로 노선ID 알아내기
            String busId = "";
            BusNumToID iInfo = xmlMapper.readValue(busURL, BusNumToID.class);
            List<BusNumToID.Body.Items.Item> busItemList =
                    iInfo.getBody().getItems().getItem();
            for(int i = 0; i<busItemList.size(); i++){
                if(busItemList.get(i).getBuslinenum().contentEquals(busNo)){
                    busId = busItemList.get(i).getLineid();
                }
            }

            //받은 노선ID로 노선정보 검색
            String cURL = """
                    https://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId?serviceKey=83f8d93455b616ee23fbbeef1a6f9882466f26f92b3847b79fe592e06ee8d689&lineid=${busId}"""
                    .replace("${busId}", busId);

            URL courseURL = new URL(cURL);
            HttpURLConnection conn4 = (HttpURLConnection) courseURL.openConnection();
            conn4.setRequestMethod("GET");

            CourseInfo cInfo = xmlMapper.readValue(courseURL, CourseInfo.class);
            List<CourseInfo.Body.Items.Item> courseItemList =
                    cInfo.getBody().getItems().getItem();

            System.out.println();
            System.out.println(busNo + "번 노선정보");
            for(int i = 0; i<courseItemList.size(); i++){
                if(i != 0 && i != courseItemList.size()-1){
                    System.out.println(courseItemList.get(i).getBstopidx()
                            + "번째 정류장 : " + courseItemList.get(i).getBstopnm());
                }
                if(i == 0){
                    System.out.println("시작지점 : " + courseItemList.get(i).getBstopnm());
                }
                if(i == courseItemList.size()-1){
                    System.out.println("종점 : " +  courseItemList.get(i).getBstopnm());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
