package com.mtcoding.airplane;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AirportInfo {
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

            @Setter
            @Getter
            public static class Items {
                private List<Item> item;

                @Setter
                @Getter
                public static class Item {
                    private String airportId;
                    private String airportNm;
                }
            }
        }
    }
}
