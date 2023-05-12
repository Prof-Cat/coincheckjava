package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.TagMap.CmcattributesEnum;

import org.slf4j.Logger;
import java.util.TreeMap;
import java.time.Instant;

public class StaticdataCMC {
    private Integer targetID;
    private static final Logger logger = Testslf4j.getLogger(StaticdataCMC.class);
    private TreeMap<String, String> cmcEndPointsTreeMap;
    private TreeMap<String, String> cmcBaseAttributesTreeMap;

    public StaticdataCMC(){
        this.targetID = 1;
        this.cmcEndPointsTreeMap = new TreeMap<>();
        this.cmcBaseAttributesTreeMap = new TreeMap<>();
    }

    public void addEndPoints(String xKey, String xSubAddress){
        if (cmcEndPointsTreeMap.containsKey(xKey) ) {
            logger.info(xKey + " is already exists");
        } else {
            cmcEndPointsTreeMap.put(xKey, xSubAddress);
            logger.info("adding endpoint of " + xKey + "::" + xSubAddress);
        }
    }
    public void setEndPointsMap( TreeMap<String, String> xMap){
        this.cmcEndPointsTreeMap.putAll(xMap);
    }

    public void addBaseAttributes(String xKey, String xAttributeString){
        if (cmcBaseAttributesTreeMap.containsKey(xKey) ) {
            logger.info(xKey + " is already exists");
        } else {
            cmcBaseAttributesTreeMap.put(xKey, xAttributeString);
            if (! xKey.equals(CmcattributesEnum.APIKEY.getValue())) {
                logger.info("adding basic attribute of " + xKey + "::" + xAttributeString);
            }
        }
    }

    public void setBaseAttributeMap( TreeMap<String, String> xMap){
        this.cmcBaseAttributesTreeMap.putAll(xMap);
    }

    public String getAttribute(String xAttribute){
        return cmcBaseAttributesTreeMap.get(xAttribute);
    }
    public String getEndPoint(CmcattributesEnum xTask){
        return cmcEndPointsTreeMap.get(CmcattributesEnum.BASEURL.getValue()) + cmcEndPointsTreeMap.get(xTask.getValue());
    }
    public String getSymbolFullMap() {
        String url = this.getEndPoint(CmcattributesEnum.IDMAP); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url, 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.IDMAP.getValue()) + jsonString);
        return jsonString;
    }

    public String getSymbolsMap(String xSymbolsString) {
        String url = this.getEndPoint(CmcattributesEnum.IDMAP) + "?symbol=" + xSymbolsString; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url, 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.IDMAP.getValue()) + jsonString);
        return jsonString;
    }

    public String getMetaDataV2Map(String xIdString) {
        String url = this.getEndPoint(CmcattributesEnum.METADATAV2) + "?id=" + xIdString; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
            logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
                cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.METADATAV2.getValue()) + jsonString);
        return jsonString;
    }

    // From hobby plan
    public String getListingHistory(Integer numberLimit) {
        long unixTimestamp = Instant.now().getEpochSecond();
        String url = this.getEndPoint(CmcattributesEnum.LISTINGHIST) + "?date=" + unixTimestamp + "&limit="+ numberLimit + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.LISTINGHIST.getValue()) + jsonString);
        return jsonString;
    }

    // From basic plan? Rejected
    public String getListingLatest(Integer numberLimit) {
        String url = this.getEndPoint(CmcattributesEnum.LISTINGLATEST) + "?limit="+ numberLimit + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.LISTINGLATEST.getValue()) + jsonString);
        return jsonString;
    }

    // From basic plan? Rejected
    public String getListingNew(Integer numberLimit) {
        String url = this.getEndPoint(CmcattributesEnum.LISTINGNEW) + "?limit="+ numberLimit + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.LISTINGNEW.getValue()) + jsonString);
        return jsonString;
    }

    public String getOHLCVHistory(String xIdString) {
        String url = this.getEndPoint(CmcattributesEnum.OHLCVHISTORY) + "?id="+ xIdString + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.OHLCVHISTORY.getValue()) + jsonString);
        return jsonString;
    }

    public String getOHLCVLatest(String xIdString) {
        String url = this.getEndPoint(CmcattributesEnum.OHLCVLATEST) + "?id="+ xIdString + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.OHLCVLATEST.getValue()) + jsonString);
        return jsonString;
    }

    public String getQuotesHistory(String xIdString, Long xStartTime) {
        String url = this.getEndPoint(CmcattributesEnum.QUOTESHISTORY) + "?id="+ xIdString + "&start_time=" + xStartTime + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue()); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.QUOTESHISTORY.getValue()) + jsonString);
        return jsonString;
    }

    public String getQuotesLatest( String xIdString ){
        String url = this.getEndPoint(CmcattributesEnum.QUOTESLATEST) + "?id="+ xIdString + "&convert=" + cmcBaseAttributesTreeMap.get(CmcattributesEnum.CURRENCY.getValue());  
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEY.getValue()));
        logger.info(Util.headerString(cmcBaseAttributesTreeMap.get(CmcattributesEnum.APIKEYHEADER.getValue()), 
            cmcBaseAttributesTreeMap.get(CmcattributesEnum.SYMBOL.getValue()), CmcattributesEnum.QUOTESLATEST.getValue()) + jsonString);
        return jsonString;
    }


}
