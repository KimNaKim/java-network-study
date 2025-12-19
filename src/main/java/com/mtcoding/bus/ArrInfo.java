package com.mtcoding.bus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "response")
public class ArrInfo {
    public Header header;
    public Body body;

    @Getter
    @Setter
    public static class Header{
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body{
        private Items items;

        @Getter
        @Setter
        public static class Items{
            @JacksonXmlElementWrapper(useWrapping = false)
            private List<Item> item;

            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Item{
                private String arsno;
                private String bstopid;
                private String bstopidx;
                private String lineno;
                private String nodenm;
                private String carno1;
                private String min1;
                private String station1;
                private String lowplate1;
                private String seat1;
                private String carno2;
                private String min2;
                private String station2;
                private String lowplate2;
                private String seat2;
                private Double gpsx;
                private Double gpsy;
                private String bustype;
                private String lineid;
            }
        }
    }

}

