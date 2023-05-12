package com.metatech.crypto.exchange;

import java.util.TreeMap;

import org.slf4j.Logger;

import com.metatech.JavaCat.Testslf4j;

public class TradingDatabase {
    private TreeMap<String, String> tradeDBAttributesTreeMap;
    private static final Logger logger = Testslf4j.getLogger(TradingDatabase.class);

    public TradingDatabase(){
        this.tradeDBAttributesTreeMap = new TreeMap<>();
    }

    public void init(String xDB, String xHost, String xPort, String xUser, String xPwd){
    }
    
    public void addDBAttribute(String xKey, String xAttributeString){
        if (tradeDBAttributesTreeMap.containsKey(xKey) ) {
            logger.info(xKey + " is already exists");
        } else {
            tradeDBAttributesTreeMap.put(xKey, xAttributeString);
            logger.info("adding endpoint of " + xKey + "::" + xAttributeString);
        }
    }

    public String getDataBaseAttribute(String xAttribute){
        return this.tradeDBAttributesTreeMap.get(xAttribute);
    }
}
