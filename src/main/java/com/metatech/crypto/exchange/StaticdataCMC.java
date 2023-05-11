package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.Util;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Map;

public class StaticdataCMC {
    // <base_url>https://pro-api.coinmarketcap.com/</base_url>
	// <static_info>v2/cryptocurrency/info</static_info>
	// <static_map>v1/cryptocurrency/map</static_map>
	// <apiHeader>X-CMC_PRO_API_KEY</apiHeader>
	// <apiKey>your key</apiKey>
	// <base_currency>jpy</base_currency>
	// <target_symbol>bitcoin</target_symbol>
    private String baseUrl;
    private String apiKey;
    private String apiKeyHeader;
    private String baseCurrency;
    private String targetSymbol;
    private Integer targetID;
    private static final Logger logger = Testslf4j.getLogger(StaticdataCMC.class);
    private TreeMap<String, String> cmcEndPointsTreeMap;

    public StaticdataCMC(){
        this.baseUrl = "https://pro-api.coinmarketcap.com/";
        this.apiKey = "";
        this.baseCurrency = "JPY";
        this.apiKeyHeader = "X-CMC_PRO_API_KEY";
        this.targetID = 1;
        this.targetSymbol = "bitcoin";
        this.cmcEndPointsTreeMap = new TreeMap<>();
    }

    public void init(String xApiKey){
        this.apiKey = xApiKey;
    }

    public String getBaseUrl(){        return this.baseUrl;    }
    public void setBaseUrl(String xUrl){  this.baseUrl = xUrl;    }
    public String getApiKey(){        return this.apiKey;    }
    public void setApiKey(String xKey){  this.apiKey = xKey;    }
    public String getApiKeyHeader(){        return this.apiKeyHeader;    }
    public void setApiKeyHeader(String xHeader){  this.apiKeyHeader = xHeader;    }
    public void setBaseCurrency( String xCurrency) { this.baseCurrency = xCurrency; }
    public String getBaseCurrency( ) { return this.baseCurrency; }
    public void setTargetSymbol( String xTargetSymbol) { this.targetSymbol = xTargetSymbol; }
    public String getTargetSymbol( ) { return this.targetSymbol; }

    public void addEndPoints(String xKey, String xSubAddress){
        if (cmcEndPointsTreeMap.containsKey(xKey) ) {
            logger.info(xKey + " is already exists");
        } else {
            cmcEndPointsTreeMap.put(xKey, xSubAddress);
            logger.info("adding endpoint of " + xKey + "::" + xSubAddress);
        }
    }

    public String getSymbolFullMap() {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("IDMap"); 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "StaticMap") + jsonString);
        return jsonString;
    }

    public String getSymbolsMap(String xSymbolsString) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("IDMap") + "?symbol=" + xSymbolsString; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url, this.apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "StaticMap") + jsonString);
        return jsonString;
    }

    public String getMetaDataV2Map(String xIdString) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("MetaDataV2") + "?id=" + xIdString; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "MetaDataV2") + jsonString);
        return jsonString;
    }

    // From hobby plan
    public String getListingHistory(Integer numberLimit) {
        long unixTimestamp = Instant.now().getEpochSecond();
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("ListingHist") + "?date=" + unixTimestamp + "&limit="+ numberLimit + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "ListingHist") + jsonString);
        return jsonString;
    }

    // From basic plan? Rejected
    public String getListingLatest(Integer numberLimit) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("ListingLatest") + "?limit="+ numberLimit + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "ListingLatest") + jsonString);
        return jsonString;
    }

    // From basic plan? Rejected
    public String getListingNew(Integer numberLimit) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("ListingNew") + "?limit="+ numberLimit + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "ListingNew") + jsonString);
        return jsonString;
    }

    public String getOHLCVHistory(String xIdString) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("OHLCVHistory") + "?id="+ xIdString + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "OHLCVHistory") + jsonString);
        return jsonString;
    }

    public String getOHLCVLatest(String xIdString) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("OHLCVLatest") + "?id="+ xIdString + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "OHLCVLatest") + jsonString);
        return jsonString;
    }

    public String getQuotesHistory(String xIdString, Long xStartTime) {
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("QuotesHistory") + "?id="+ xIdString + "&start_time=" + xStartTime + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url,apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "QuotesHistory") + jsonString);
        return jsonString;
    }

    // cmc-v2quotes.sh:curl -XGET 
    // -H'content-type: application/json' 
    // -H'x-cmc_pro_api_key: mykey' 
    // -d 'id=1&convert=USD' 
    // -G https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest
    public String getQuotesLatest( String xIdString ){
        String url = this.getBaseUrl() + cmcEndPointsTreeMap.get("QuotesLatest") + "?id="+ xIdString + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url, this.apiKeyHeader, this.apiKey);
        logger.info(Util.headerString(this.apiKeyHeader, this.targetSymbol, "QuotesLatest") + jsonString);
        return jsonString;
    }


}
