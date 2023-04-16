package com.metatech.crypto.exchange;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class ConfigFileTest {
    @Test
    public void testFileRead() {
        try {
            Map<String, Configuration> myConfigMap = Configuration.loadConfig("./cnf/exchange.cnf.xml");
            assertNotNull(myConfigMap.containsKey("coincheck"));
            //assertEquals(null, 0, 0, 0);
        } catch ( Exception e) {
            System.out.println(e.toString());
        }
      }
}

