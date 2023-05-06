package com.metatech.crypto.exchange;

public enum CoinCheckOrderType {
    LIMTTBUY("buy"),
    LIMITSELL("sell"),
    MARKETBUY("market_buy"),
    MARKETSELL("market_sell");

    private final String value;

    private CoinCheckOrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
