package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.TagMap.ExchangeEnum;

import java.util.TreeMap;

import org.slf4j.Logger;

public class ExchangeX {

    private static final Logger logger = Testslf4j.getLogger(ExchangeX.class);
    private TreeMap<String, String> attributesMap = new TreeMap<>();

    public ExchangeX( TreeMap<String, String> xMap){
        this.attributesMap.putAll(xMap);
        logger.info(this.attributesMap.get(ExchangeEnum.EXHANGENAME.getValue()));
    }
    
    public String getApiKey(){ return this.attributesMap.get(ExchangeEnum.APIKEY.getValue());}
    public String getSecret(){ return this.attributesMap.get(ExchangeEnum.APISECRET.getValue());    }
    public String getBaseUrl(){  return this.attributesMap.get(ExchangeEnum.BASEURL.getValue()); }
    public String getExchangeName(){ return this.attributesMap.get(ExchangeEnum.EXHANGENAME.getValue()); }
    public String getCoinPair(){ return this.attributesMap.get(ExchangeEnum.SYMBOL.getValue()) + "_" + this.attributesMap.get(ExchangeEnum.CURRENCY.getValue()); }
    public String getCoin(){ return this.attributesMap.get(ExchangeEnum.SYMBOL.getValue());}
    public String getCurrency(){ return this.attributesMap.get(ExchangeEnum.CURRENCY.getValue());}
}
