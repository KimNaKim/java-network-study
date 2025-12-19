package com.mtcoding.crime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CrimeInfo {
    //파싱받은 객체 행 분리용
    @Getter
    @Setter

    private String region;
    private String major;
    private String minor;
    private Integer count;

    public CrimeInfo(String region, String major, String minor, Integer count) {
        this.region = region;
        this.major = major;
        this.minor = minor;
        this.count = count;
    }
}
