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
import java.util.Map;

public class Marketdata {
    private String apiKey;
    private String apiSecret;
    public static Configuration targetExchange;
    private static final Logger logger = Testslf4j.getLogger(Marketdata.class);

    public Marketdata(String xExchange, Map<String, Configuration> theMap) {
        try {
            targetExchange = theMap.get(xExchange);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("Marketdata initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("Marketdata initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
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

    // GET /api/accounts/balance
    public String getAccountBalance() {
        String url = targetExchange.getBaseUrl() + "accounts/balance";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }

    // GET /api/accounts
    public String getAccountInfo() {
        String url = targetExchange.getBaseUrl() + "accounts";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }

    // GET /api/exchange/orders/opens
    public String getAccountOrders() {
        String url = targetExchange.getBaseUrl() + "exchange/orders/opens";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }

    // GET /api/exchange/orders/transactions_pagination
    public String getTransactionPages() {
        String url = targetExchange.getBaseUrl() + "exchange/orders/transactions_pagination";
        String jsonString = requestByUrlWithHeader(url);
        return jsonString;
    }

    // Cancel <id> : DELETE /api/exchange/orders/<id>
    // Request <id> cancel status : GET /api/exchange/orders/cancel_status?id=[id]

    // GET /api/exchange/orders/transactions
    public String getTransactions() {
        String url = targetExchange.getBaseUrl() + "exchange/orders/transactions";
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
    
        // {'ACCESS-KEY': 'yourapi-key', 'ACCESS-NONCE': '1682422247275682048', 'ACCESS-SIGNATURE': 'the signed message'}
        // send : https://coincheck.com/api/accounts/balance

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
        long currentUnixTime = System.currentTimeMillis();
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
