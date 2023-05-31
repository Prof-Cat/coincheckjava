package com.metatech.crypto.exchange;

public class TagMap {

    public TagMap(){
        // to avoid implicit constructor error
    }
    
    public enum CmcattributesEnum{
        APIKEYHEADER("apiKeyHeader"),
        APIKEY("apiKey"),
        CURRENCY("currency"),
        COIN("symbol"),
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

    public enum ExchangeAccessEnum{
        APISECRET("apiSecret"),
        APIKEY("apiKey"),
        CURRENCY("currency"),
        COIN("symbol"),
        BASEURL("BaseUrl"),
        EXHANGENAME("execName"),
        REALTIME("realtime"),
        ORDERENDPOINT("endpoint_orders"),
        OPENSENDPOINT("endpoint_opens"),
        NEWORDER("newOrder"),
        CANCELSTATUS("endpoint_cancelstatus"),
        TXSENDPOINT("endpoint_txs"),
        PAGEDTXS("endpoint_pagedtxs"),
        ACCTBALLANCE("endpoint_acctbalance"),
        ACCTINFO("endpoint_acctinfo"),
        PUBTICKER("endpoint_pticker"),
        PUBTRADES("endpoint_trades"),
        ORDERBOOKS("endpoint_orderbook");

        private final String value;
        private ExchangeAccessEnum(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    // TOBE replaced
    public enum OrderStatusNameEnum {
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
        private OrderStatusNameEnum(String value) { this.value = value;        }
        public String getValue() { return this.value;}
    }

    public enum OrdStatusEnum {
        LOCAL('L'),
        NEW('0'),
        PARTIALFILL('1'),
        FULLFILL('2'),
        DFD('3'),
        CANCELED('4'),
        REPLACED('5'),
        PENDINGCANCEL('6'),
        STOPPED('7'),
        REJECTED('8'),
        SUSPENDED('9'),
        PENDINGNEW('A'),
        CALCULATED('B'),
        EXPIRED('C'),
        ACCEPTFORBIDDING('D'),
        PENDINGREPLACE('E');

        // to keep simple, not going to handle amendments, only new order, cancel
    
        private final char value;
        private OrdStatusEnum( char value) { this.value = value;        }
        public char getValue() { return this.value;}

        public static OrdStatusEnum fromValue(char value) {
            for (OrdStatusEnum myEnum : OrdStatusEnum.values()) {
                if (myEnum.value == value) {
                    return myEnum;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
        // public static String nameOfValue(char value){
        //     switch (value){
        //         case OrdStatusEnum.CANCELED.getValue() :
        //             return OrderStatusNameEnum.CANCELED.getValue();
        //             break;
        //         case OrdStatusEnum.REJECTED.getValue():
        //             return 
        //             break;
        //         default:
        //             break;
        //     }
        // }
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

    public enum ExDestination{
        COINCHECK("CoinCheck"),
        ZAIF("Zaif"),
        GMO("GMO"),
        BINANCE("Binance"),
        OKEX("OKEX"),
        MTX("Mtx");

        private final String value;
        private ExDestination(String xValue) { this.value = xValue;        }
        public String getValue() { return this.value;}

        public static ExDestination fromValue(String value) {
            for (ExDestination myEnum : ExDestination.values()) {
                if (myEnum.value.equals(value)) {
                    return myEnum;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    public enum BaseCurrency{
        USD("USD"),
        JPY("JPY"),
        EURO("EURO"),
        HKD("HKD"),
        SGD("SGD"),
        CNY("CNY"),
        USDC("USDC"),
        USDT("USDT");

        private final String value;
        private BaseCurrency(String xValue) { this.value = xValue;        }
        public String getValue() { return this.value;}

        public static BaseCurrency fromValue(String value) {
            for (BaseCurrency myEnum : BaseCurrency.values()) {
                if (myEnum.value.equals(value)) {
                    return myEnum;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
