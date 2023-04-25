package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
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
import java.text.SimpleDateFormat;
import java.util.Date;
// import java.util.HashMap;
import java.util.Map;

public class Marketdata {
    private String apiKey;
    private String apiSecret;
    public static final String helpUsage = "Marketdata -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(Marketdata.class);
    public static Configuration targetExchange;

    public static void main(String[] args) {
        
        String exchCode = System.getProperty("TARGET");
        String cfgFile = System.getProperty("CONFIG");

        Properties props = System.getProperties();
        props.list(System.out);
        
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            System.out.println(key + ": " + env.get(key));
        }
        
        if ( cfgFile == null ){
            System.out.print( helpUsage);
            System.exit(-1);
        }
        try {
            // Load the log4j2.xml or log4j2.properties file
            System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));

            logger.info("CONFIG: " + cfgFile);
            Map<String, Configuration> myConfigMap = Configuration.loadConfig(cfgFile);

            Marketdata api = new Marketdata(exchCode, myConfigMap);
            // logger.info(api.getTicker());
            for ( int i=0; i<1; i++){
                logger.info("TargetExchange=" + exchCode + "::TargetPair=" + api.targetExchange.coinPair  + "::Type=ticker::" + api.getPublicTicker());
                logger.info("TargetExchange=" + exchCode + "::TargetPair=" + api.targetExchange.coinPair  + "::Type=execution::" + api.getPublicTrades());
                logger.info("TargetExchange=" + exchCode + "::TargetPair=" + api.targetExchange.coinPair  + "::Type=orderbook::" + api.getPublicOrderBooks());
                Thread.sleep(1000);
            }

            logger.info(api.getAccountBalance());
            //logger.info(api.getAccountInfo());
            //logger.info(api.getAccountOrders());
            
            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

    public Marketdata(String xExchange, Map<String, Configuration> theMap) {
        targetExchange = theMap.get(xExchange);
        apiKey = targetExchange.getApiKey();
        apiSecret = targetExchange.getSecret();
    }

    public String getPublicTicker() {
        String url = targetExchange.getBaseUrl() + "/ticker"; 
        String jsonString = requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicTrades() {
        String url = targetExchange.getBaseUrl() + "/trades?pair=" + targetExchange.getCoinPair(); 
        String jsonString = requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicOrderBooks() {
        String url = targetExchange.getBaseUrl() + "/order_books?pair=" + targetExchange.getCoinPair(); 
        String jsonString = requestByPublicUrl(url);
        return jsonString;
    }

    public String getPrivateTicker() {
        String url = targetExchange.getBaseUrl() + "/accounts/ticker?pair=" + targetExchange.getCoinPair();
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }

    public String getAccountBalance() {
        String url = targetExchange.getBaseUrl() + "accounts/balance";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }
    public String getAccountInfo() {
        String url = targetExchange.getBaseUrl() + "accounts";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }
    public String getAccountOrders() {
        String url = targetExchange.getBaseUrl() + "orders/opens";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }
    private String requestByUrlWithHeader(String url){
        HttpClient client = HttpClient.newHttpClient();
        String nonce = createNonce();
        logger.info("sending request to " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("ACCESS-KEY", apiKey)
                .header("ACCESS-NONCE", nonce)
                .header("ACCESS-SIGNATURE", createSignature(nonce + url))
                .GET()
                .build();
    
        String jsonString;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonString = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            jsonString = null;
        }
        return jsonString;
    }

    private String createSignature(String nonce) {
        String message = nonce;
        return HMAC_SHA256Encode(apiSecret, message);
    }

    private String createNonce() {
        long currentUnixTime = System.currentTimeMillis() / 1000L;
        String nonce = String.valueOf(currentUnixTime);
        return nonce;
    }

    private String requestByPublicUrl(String url) {
        String jsonString;
        logger.info(url);
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            jsonString = httpResponse.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            jsonString = null;
        }
        return jsonString;
    }

    public static String HMAC_SHA256Encode(String secretKey, String message) {

        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(),"hmacSHA256");

        Mac mac = null;
        try {
            mac = Mac.getInstance("hmacSHA256");
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // can't recover
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            // can't recover
            throw new RuntimeException(e);
        }
        byte[] rawHmac = mac.doFinal(message.getBytes());
        return Hex.encodeHexString(rawHmac);
    }
}
