package com.metatech.crypto.exchange.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

@XmlRootElement(name = "fields")
public class FixFields {
    private List<FixField> fieldList;

    @XmlElement(name = "field")
    public List<FixField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FixField> fieldList) {
        this.fieldList = fieldList;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class FixField {
    @XmlAttribute
    private int number;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String type;

    @XmlElement(name = "value")
    private List<FixValue> values;

    // Getters and setters for the fields

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FixValue> getValues() {
        return values;
    }

    public void setValues(List<FixValue> values) {
        this.values = values;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class FixValue {
    @XmlAttribute
    private String enumValue;

    @XmlAttribute
    private String description;

    // Getters and setters for the fields

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
