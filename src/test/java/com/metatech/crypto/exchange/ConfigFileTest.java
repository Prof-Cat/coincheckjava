package com.metatech.crypto.exchange;

import org.junit.Test;

import com.metatech.crypto.exchange.TagMap.ExchangeAccessEnum;

import static org.junit.Assert.*;
import java.util.Map;

public class ConfigFileTest {
    @Test
    public void testFileRead() {
        try {
            Configuration.loadConfig("./cnf/exchange.cnf.xml");
            assertNotNull(Configuration.primaryExchAttributeTreeMap.get(ExchangeAccessEnum.EXHANGENAME.getValue()));
            //assertEquals(null, 0, 0, 0);
        } catch ( Exception e) {
            System.out.println(e.toString());
        }
      }
}

