package com.mtcoding.bus;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "response")
public class StopInfo {
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
        private Integer pageNo;
        private Integer numOfRows;
        private Integer totalCount;

        @Getter
        @Setter
        public static class Items{
            @JacksonXmlElementWrapper(useWrapping = false)
            private List<Item> item;

            @Getter
            @Setter
            public static class Item{
                private String bstopid;
                private String bstopnm;
                private String arsno;
                private Double gpsx;
                private Double gpsy;
                private String stoptype;
            }
        }
    }

}
