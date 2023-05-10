package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.Util;
import org.slf4j.Logger;
import java.util.Properties;

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
    private String apiHeader;
    private String baseCurrency;
    private String targetSymbol;
    private Integer targetID;
    private static final Logger logger = Testslf4j.getLogger(StaticdataCMC.class);

    public StaticdataCMC(){
        this.baseUrl = "https://pro-api.coinmarketcap.com/";
        this.apiKey = "";
        this.baseCurrency = "JPY";
        this.apiHeader = "X-CMC_PRO_API_KEY";
        this.targetID = 1;
        this.targetSymbol = "bitcoin";
    }

    public void init(String xApiKey){
        this.apiKey = xApiKey;
    }

    public String getBaseUrl(){        return this.baseUrl;    }
    public void setBaseUrl(String xUrl){  this.baseUrl = xUrl;    }
    public String getApiKey(){        return this.apiKey;    }
    public void setApiKey(String xKey){  this.apiKey = xKey;    }
    public String getApiHeader(){        return this.apiHeader;    }
    public void setApiHeader(String xHeader){  this.apiHeader = xHeader;    }

    public String getSymbolMap() {
        String url = this.getBaseUrl() + "/v1/cryptocurrency/map"; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByPublicUrl(url);
        logger.info(Util.headerString(this.apiHeader, this.targetSymbol, "StaticMap") + jsonString);
        return jsonString;
    }

    // cmc-v2quotes.sh:curl -XGET 
    // -H'content-type: application/json' 
    // -H'x-cmc_pro_api_key: mykey' 
    // -d 'id=1&convert=USD' 
    // -G https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest
    public String getSymbolQuotes( int xID ){
        String url = this.getBaseUrl() + "v2/cryptocurrency/quotes/latest?id=" + xID + "&convert=" + this.baseCurrency; 
        String jsonString = com.metatech.crypto.exchange.Util.requestByUrlWithHeaderParameters(url, this.apiKey);
        return jsonString;
    }


}
