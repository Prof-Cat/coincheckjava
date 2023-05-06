package com.metatech.crypto.exchange;

public enum CoinCheckOrderStatus {
    PENDING("pending"),
    NEW("new"),
    PARTIALFILL("pfill"),
    FULLFILL("ffill"),
    CANCELED("canceled"),
    EXPIRED("expired"),
    REJECTED("rejected");
    // to keep siimple, not going to handle amendments, only new order, cancel

    private final String value;

    private CoinCheckOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
