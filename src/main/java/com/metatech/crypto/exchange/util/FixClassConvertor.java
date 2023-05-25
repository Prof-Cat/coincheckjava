package com.metatech.crypto.exchange.util;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

public class FixClassConvertor {

    private static final Logger logger = Testslf4j.getLogger(FixClassConvertor.class);

    public static void main(String[] args) {
        try {
            // Load XML file and unmarshal to Java object
            File xmlFile = new File("/Users/jamesqu/github/coincheckjava/src/main/java/com/metatech/crypto/exchange/resources/FIX42.xml");
 
            // // Create JAXBContext and Unmarshaller
            // JAXBContext jaxbMsgContext = JAXBContext.newInstance(FixMessages.class);
            // Unmarshaller msgUnmarshaller = jaxbMsgContext.createUnmarshaller();

            // JAXBContext jaxbFieldContext = JAXBContext.newInstance(FixFields.class);
            // Unmarshaller fieldUnmarshaller = jaxbFieldContext.createUnmarshaller();

            // List<Field> myFields = (List<Field>) fieldUnmarshaller.unmarshal(xmlFile);
            
            // List<Message> myMessages = (List<Message>) msgUnmarshaller.unmarshal(xmlFile);

            JAXBContext fixContext = JAXBContext.newInstance(Fix42.class);
            Unmarshaller fixUnmarshaller = fixContext.createUnmarshaller();
            Fix42 fix42Dictionary = (Fix42) fixUnmarshaller.unmarshal(xmlFile);
            List<Field> fixFields = fix42Dictionary.getFields();

            for (Field field : fixFields ) {
                int number = field.getNumber();
                String name = field.getName();
                String type = field.getType();
                List<Value> values = field.getValues();

                System.out.println("Field Number: " + number);
                System.out.println("Field Name: " + name);
                System.out.println("Field Type: " + type);

                if (values != null) {
                    for (Value value : values) {
                        String enumValue = value.getEnumValue();
                        String description = value.getDescription();
                        System.out.println("Value Enum: " + enumValue);
                        System.out.println("Value Description: " + description);
                    }
                }

                System.out.println();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
