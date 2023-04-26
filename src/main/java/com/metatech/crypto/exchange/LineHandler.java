package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineHandler {
    private String apiKey;
    private String apiSecret;
    public static Configuration targetExchange;
    private static final Logger logger = Testslf4j.getLogger(LineHandler.class);

    public LineHandler(String xExchange, Map<String, Configuration> theMap) {
        try {
            targetExchange = theMap.get(xExchange);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("LineHandler initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("LineHandler initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
    }
}
