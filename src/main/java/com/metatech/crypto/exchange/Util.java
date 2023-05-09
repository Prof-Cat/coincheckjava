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
import java.net.http.HttpRequest.BodyPublisher;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Util {

    // TODO : make methods thread safe
    public static String requestByUrlWithHeader(String url, String apiKey, String apiSecret){
        HttpClient client = HttpClient.newHttpClient();
        String nonce = Util.createNonce();
        // logger.info("sending request to " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("ACCESS-KEY", apiKey)
                .header("ACCESS-NONCE", nonce)
                .header("ACCESS-SIGNATURE", Util.createSignature(nonce + url, apiSecret))
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

    public static String requestByUrlWithHeaderParameters(String url, String apiKey){
        HttpClient client = HttpClient.newHttpClient();
        // cmc-v2quotes.sh:curl -XGET -H'content-type: application/json' 
        // -H'x-cmc_pro_api_key: b2ca43e6-ea9d-4140-8642-c300a2045095' 
        // -d 'id=1&convert=USD' -G https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-cmc_pro_api_key", apiKey)
                .header("content-type", "application/json")
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

    public static String deleteByUrlWithHeader(String url, String apiKey, String apiSecret){
        HttpClient client = HttpClient.newHttpClient();
        String nonce = Util.createNonce();
        // logger.info("sending request to " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("ACCESS-KEY", apiKey)
                .header("ACCESS-NONCE", nonce)
                .header("ACCESS-SIGNATURE", Util.createSignature(nonce + url, apiSecret))
                .DELETE()
                .build();

        // {'ACCESS-KEY': 'yourapi-key', 'ACCESS-NONCE': '1682422247275682048', 'ACCESS-SIGNATURE': 'the signed message'}
        // send : https://coincheck.com/api/exchange/orders/<id>

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

    public static String postByUrlWithHeader(String url, String apiKey, String apiSecret, String xBody){
        HttpClient client = HttpClient.newHttpClient();
        String nonce = Util.createNonce();
        // logger.info("sending request to " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("ACCESS-KEY", apiKey)
                .header("ACCESS-NONCE", nonce)
                .header("ACCESS-SIGNATURE", Util.createSignature(nonce + url + xBody, apiSecret))
                .POST(HttpRequest.BodyPublishers.ofString(xBody,StandardCharsets.UTF_8 ))
                .build();

        // {'ACCESS-KEY': 'yourapi-key', 'ACCESS-NONCE': '1682422247275682048', 'ACCESS-SIGNATURE': 'the signed message'}
        // send : https://coincheck.com/api/exchange/orders/<id>

        String jsonString;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            System.out.println(response.headers());

            jsonString = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            jsonString = null;
        }
        return jsonString;
    }

    public static String createSignature(String nonce, String apiSecret) {
        return HMAC_SHA256Encode(apiSecret, nonce);
    }

    public static String createNonce() {
        long currentUnixTime = System.currentTimeMillis();
        String nonce = String.valueOf(currentUnixTime);
        return nonce;
    }

    public static String requestByPublicUrl(String url) {
        String jsonString;
        //logger.info(url);
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
        } catch (Exception e1) {
            e1.printStackTrace();
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

    public static String headerString( String xExchange, String xCoinPair, String xDataType){
        return "Exchange::" + xExchange + "::CoinPair::" + xCoinPair + "::DataType::" + xDataType;
    }

    public static String generateNextClOrdID(){
        return "testOrderID";   // to be thread safe sequencial formated string
    }
}