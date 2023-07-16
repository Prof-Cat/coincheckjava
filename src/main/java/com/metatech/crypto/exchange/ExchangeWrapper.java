package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.util.TagMap.ExchangeAccessEnum;

import java.util.TreeMap;

import org.slf4j.Logger;

public class ExchangeWrapper {

    private static final Logger logger = Testslf4j.getLogger(ExchangeWrapper.class);
    private TreeMap<String, String> attributesMap = new TreeMap<>();

    public ExchangeWrapper( TreeMap<String, String> xMap){
        this.attributesMap.putAll(xMap);
        logger.info(this.attributesMap.get(ExchangeAccessEnum.EXHANGENAME.getValue()));
    }
    
    public String getApiKey(){ return this.attributesMap.get(ExchangeAccessEnum.APIKEY.getValue());}
    public String getSecret(){ return this.attributesMap.get(ExchangeAccessEnum.APISECRET.getValue());    }
    public String getBaseUrl(){  return this.attributesMap.get(ExchangeAccessEnum.BASEURL.getValue()); }
    public String getExchangeName(){ return this.attributesMap.get(ExchangeAccessEnum.EXHANGENAME.getValue()); }
    public String getCoinPair(){ return this.attributesMap.get(ExchangeAccessEnum.COIN.getValue()) + "_" + this.attributesMap.get(ExchangeAccessEnum.CURRENCY.getValue()); }
    public String getCoin(){ return this.attributesMap.get(ExchangeAccessEnum.COIN.getValue());}
    public String getCurrency(){ return this.attributesMap.get(ExchangeAccessEnum.CURRENCY.getValue());}

    public String orderEndPoint(){ 
        return this.attributesMap.get(ExchangeAccessEnum.BASEURL.getValue()) + 
            this.attributesMap.get(ExchangeAccessEnum.ORDERENDPOINT.getValue());
    }

    public String opensEndPoint(){ 
        return this.orderEndPoint() + 
            this.attributesMap.get(ExchangeAccessEnum.OPENSENDPOINT.getValue());
    }

    public String transactionEndPoint(){ 
        return this.orderEndPoint() + 
            this.attributesMap.get(ExchangeAccessEnum.TXSENDPOINT.getValue());
    }

    public String cancelStatusEndPoint(){ 
        return this.orderEndPoint() + 
            this.attributesMap.get(ExchangeAccessEnum.CANCELSTATUS.getValue());
    }

    public String pagedTxsEndPoint(){
        return this.orderEndPoint() +
            this.attributesMap.get(ExchangeAccessEnum.PAGEDTXS.getValue());
    }

    public String accountInfoEndPoint(){
        return this.getBaseUrl() +
            this.attributesMap.get(ExchangeAccessEnum.ACCTINFO.getValue());
    }

    public String accountBallanceEndPoint(){
        return this.getBaseUrl() +
            this.attributesMap.get(ExchangeAccessEnum.ACCTBALLANCE.getValue());
    }
    public String tickerEndPoint(){
        return this.getBaseUrl() +
            this.attributesMap.get(ExchangeAccessEnum.PUBTICKER.getValue());
    }

    public String tradesEndPoint(){
        return this.getBaseUrl() +
            this.attributesMap.get(ExchangeAccessEnum.PUBTRADES.getValue());
    }

    public String orderBookEndPoint(){
        return this.getBaseUrl() +
            this.attributesMap.get(ExchangeAccessEnum.ORDERBOOKS.getValue());
    }
}

