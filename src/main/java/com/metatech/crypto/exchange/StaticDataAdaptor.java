package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class StaticDataAdaptor {

    public static final String helpUsage = "StaticDataAdaptor -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(StaticDataAdaptor.class);
    public static ExchangeX targetExchange;
    protected static StaticdataCMC staticdataCMC;
    protected static Map<String, ExchangeX> myConfigMap;

    public static void main(String[] args) {
        
        String exchCode = System.getProperty("TARGET");
        String cfgFile = System.getProperty("CONFIG");
                
        if ( cfgFile == null ){
            System.out.print( helpUsage);
            System.exit(-1);
        }
        try {
            // Load the log4j2.xml or log4j2.properties file
            System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
            logger.info("CONFIG: " + cfgFile);
            initStaticData(exchCode,cfgFile);
            
            String myClassPath = System.getProperty("java.class.path");
            logger.info(myClassPath);
            staticdataCMC.getSymbolMap();

            logger.info(staticdataCMC.getSymbolQuotes(1));

            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

    public static void initStaticData(String exchCode, String cfgFile){
        try {
            myConfigMap = Configuration.loadConfig(cfgFile);
            staticdataCMC = Configuration.geStaticdataCMC();
        } catch ( Exception e){
            logger.error(e.toString());
        }
    }

}
