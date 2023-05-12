package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StaticDataAdaptor {

    public static final String helpUsage = "StaticDataAdaptor -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(StaticDataAdaptor.class);
    public static ExchangeX targetExchange;
    protected static StaticdataCMC staticdataCMC;

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
            
            staticdataCMC.getSymbolsMap("BTC,ETH");
            logger.info("================================");
            staticdataCMC.getMetaDataV2Map("1,2");
            logger.info("================================");
            staticdataCMC.getListingHistory(7);
            logger.info("================================");
            staticdataCMC.getListingLatest(2);
            logger.info("================================");
            staticdataCMC.getListingNew(2);
            logger.info("================================");
            logger.info(staticdataCMC.getQuotesLatest("1,2"));
            logger.info("================================");
            staticdataCMC.getOHLCVHistory("1,2");
            logger.info("================================");
            staticdataCMC.getOHLCVLatest("1,2");

            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

    public static void initStaticData(String exchCode, String cfgFile){
        try {
            Configuration.loadConfig(cfgFile);
            staticdataCMC = new StaticdataCMC();
            staticdataCMC.setBaseAttributeMap( Configuration.cmcBaseAttributesTreeMap );
            staticdataCMC.setEndPointsMap(Configuration.cmcEndPointsTreeMap);
        } catch ( Exception e){
            logger.error(e.toString());
        }
    }

}
