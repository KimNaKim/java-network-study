package com.mtcoding.airplane;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AirInfo {
    private Response response;

    @Setter
    @Getter
    public static class Response {
        private Header header;
        private Body body;

        @Setter
        @Getter
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Setter
        @Getter
        public static class Body{
            private Items items;
            private Integer pageNo;
            private Integer numOfRows;
            private Integer totalCount;

            @Setter
            @Getter
            public static class Items {
                private List<Item> item;

                @Setter
                @Getter
                public static class Item {
                    private String airlineNm;
                    private String arrAirportNm;
                    private String arrPlandTime;
                    private String depAirportNm;
                    private String depPlandTime;
                    private Integer economyCharge;
                    private Integer prestigeCharge;
                    private String vihicleId;
                }
            }
        }
    }

}