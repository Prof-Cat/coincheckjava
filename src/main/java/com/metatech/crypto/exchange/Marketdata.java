package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;

import com.google.api.client.http.*;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Marketdata {
    private String apiKey;
    private String apiSecret;
    public static final String helpUsage = "MarketData -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
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
            logger.info(api.getPublicTicker());
            logger.info(api.getBalance());
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

    public Marketdata(String xExchange, Map<String, Configuration> theMap) {
        targetExchange = theMap.get(xExchange);
        apiKey = targetExchange.getApiKey();
        apiSecret = targetExchange.getSecret();
    }


    public String getTicker() {
        // String url = "https://coincheck.com/api/accounts/ticker";
        String url = targetExchange.getUrl() + "/ticker";
        String jsonString = requestByUrlWithHeader(url, createHeader(url));
        return jsonString;
    }

    public String getPublicTicker() {
        String url = "https://coincheck.com/api/ticker";
        String jsonString = requestByUrl(url);
        return jsonString;
    }

    public String getBalance() {
        String jsonString = requestByUrlWithHeader(targetExchange.getUrl(), createHeader(targetExchange.getUrl()));
        return jsonString;
    }

    private Map<String, String> createHeader(String url) {
        Map<String, String> map = new HashMap<String, String>();
        String nonce = createNonce();
        map.put("ACCESS-KEY", apiKey);
        map.put("ACCESS-NONCE", nonce);
        map.put("ACCESS-SIGNATURE", createSignature(nonce));
        return map;
    }

    private String createSignature(String nonce) {
        String message = nonce + targetExchange.getUrl();
        return HMAC_SHA256Encode(apiSecret, message);
    }

    private String createNonce() {
        long currentUnixTime = System.currentTimeMillis() / 1000L;
        String nonce = String.valueOf(currentUnixTime);
        return nonce;
    }

    private String requestByUrlWithHeader(String url, final Map<String, String> headers){
        ApacheHttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(final HttpRequest request) throws IOException {
                request.setConnectTimeout(0);
                request.setReadTimeout(0);
                request.setParser(new GsonFactory().createJsonObjectParser());
                final HttpHeaders httpHeaders = new HttpHeaders();
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    httpHeaders.set(e.getKey(), e.getValue());
                }
                request.setHeaders(httpHeaders);
            }
        });
        String jsonString;
        try {
            HttpRequest request = factory.buildGetRequest(new GenericUrl(url));
            HttpResponse response = request.execute();
            jsonString = response.parseAsString();
        } catch (IOException e) {
            e.printStackTrace();
            jsonString = null;
        }
        return jsonString;
    }

    private String requestByUrl(String url){
        ApacheHttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(final HttpRequest request) throws IOException {
                request.setConnectTimeout(0);
                request.setReadTimeout(0);
                request.setParser(new GsonFactory().createJsonObjectParser());
            }
        });
        String jsonString;
        try {
            HttpRequest request = factory.buildGetRequest(new GenericUrl(url));
            HttpResponse response = request.execute();
            jsonString = response.parseAsString();
        } catch (IOException e) {
            e.printStackTrace();
            jsonString = null;
        }
        return jsonString;
    }

    public static String HMAC_SHA256Encode(String secretKey, String message) {

        SecretKeySpec keySpec = new SecretKeySpec(
                secretKey.getBytes(),
                "hmacSHA256");

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
