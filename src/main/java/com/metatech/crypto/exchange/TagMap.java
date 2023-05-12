package com.metatech.crypto.exchange;

public class TagMap {

    public enum CmcattributesEnum{
        APIKEYHEADER("apiKeyHeader"),
        APIKEY("apiKey"),
        CURRENCY("currency"),
        SYMBOL("symbol"),
        BASEURL("BaseUrl"),
        IDMAP("IDMap"),
        METADATAV2("MetaDataV2"),
        LISTINGHIST("ListingHist"),
        LISTINGLATEST("ListingLatest"),
        LISTINGNEW("ListingNew"),
        OHLCVHISTORY("OHLCVHistory"),
        OHLCVLATEST("OHLCVLatest"),
        QUOTESHISTORY("QuotesHistory"),
        QUOTESLATEST("QuotesLatest");

        private final String value;
        private CmcattributesEnum(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    public enum ExchangeEnum{
        APISECRET("apiSecret"),
        APIKEY("apiKey"),
        CURRENCY("currency"),
        SYMBOL("symbol"),
        BASEURL("BaseUrl"),
        EXHANGENAME("execName"),
        REALTIME("realtime");

        private final String value;
        private ExchangeEnum(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    public enum OrderStatusEnum {
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
        private OrderStatusEnum(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    public enum TimeInForceEnum{
        DAY(0),
        GTC(1),
        OPG(2),
        IOC(3),
        FOK(4),
        GTX(5),
        GLD(6),
        GLT(7);

        private final Integer value;
        private TimeInForceEnum(Integer xValue) { this.value = xValue;        }
        public Integer getValue() { return this.value;}
    }
}
