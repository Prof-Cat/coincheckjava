package com.metatech.crypto.exchange;

public class TagMap {
    public enum OrderStatus {
        LOCAL("local"),
        PENDING("pending"),
        NEW("new"),
        PARTIALFILL("pfill"),
        FULLFILL("ffill"),
        CANCELED("canceled"),
        EXPIRED("expired"),
        REJECTED("rejected");
        // to keep siimple, not going to handle amendments, only new order, cancel
    
        private final String value;
        private OrderStatus(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    public enum TimeInForce{
        DAY(0),
        GTC(1),
        OPG(2),
        IOC(3),
        FOK(4),
        GTX(5),
        GLD(6),
        GLT(7);

        private final Integer value;
        private TimeInForce(Integer xValue) { this.value = xValue;        }
        public Integer getValue() { return this.value;}
    }
}
