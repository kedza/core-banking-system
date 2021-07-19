package com.wonderlabz.account.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    public LocalDateTime unmarshal(String value) throws Exception {
        return LocalDateTime.parse(value);
    }

    public String marshal(LocalDateTime value) throws Exception {
        return value.toString();
    }
}