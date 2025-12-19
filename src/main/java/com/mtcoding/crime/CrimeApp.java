package com.mtcoding.crime;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sun.tools.javac.Main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.*;

public class CrimeApp {
    public static void main(String[] args) {
        try {
            //1. csv 파일 자바 객체로 파싱하기
            //1-1. 행분리를 위해 csv 파일 원본 받기
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            InputStream is = Main.class.getClassLoader().getResourceAsStream("crimes.csv");
            Reader reader = new InputStreamReader(is, Charset.forName("CP949"));
            MappingIterator<RawToPro> it =
                    mapper.readerFor(RawToPro.class)
                            .with(schema)
                            .readValues(reader);

            List<RawToPro> rawRows = it.readAll();

            //파싱한 데이터 가공하기(지역 행분리)
            List<CrimeInfo> result = new ArrayList<>();
            for(RawToPro row : rawRows){
                for(Map.Entry<String, String> entry
                        :row.getRegionCounts().entrySet()){
                    String region = entry.getKey();
                    String value = entry.getValue();

                    //빈 값 방지하기
                    if(value == null || value.isBlank()) continue;

                    int count = Integer.parseInt(value);

                    result.add(new CrimeInfo(
                            region,
                            row.getMajor(),
                            row.getMinor(),
                            count
                    ));
                }
            }

            Set<String> regions = new TreeSet<>();

            for (CrimeInfo info : result) {
                regions.add(info.getRegion());
            }
            System.out.println("입력 가능한 지역 목록 : ");
            for (String region : regions) {
                System.out.println(region);
            }

            //지역 입력받고 입력 바탕으로 범죄율 계산하기
            Scanner keyboard = new Scanner(System.in);
            System.out.print("범죄율 비율을 보고 싶은 지역을 입력하세요. : ");
            String regionInput = keyboard.nextLine();
            Double percent = CrimeCal.showCrimePer(result, regionInput);
            System.out.printf(
                    "%s의 범죄비율 : %.1f%%\n",
                    regionInput,
                    percent
            );


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
