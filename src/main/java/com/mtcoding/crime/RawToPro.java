package com.mtcoding.crime;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class RawToPro {
    //csv 원본 파싱용

    @Getter
    @Setter
    @JsonProperty("범죄대분류")
    private String major;

    @Getter
    @Setter
    @JsonProperty("범죄중분류")
    private String minor;

    //지역 컬럼들을 담는 컬렉션
    @Getter
    private Map<String, String> regionCounts = new HashMap<>();

    @JsonAnySetter
    public void addRegion(String key, String value){
        regionCounts.put(key, value);
    }

}
