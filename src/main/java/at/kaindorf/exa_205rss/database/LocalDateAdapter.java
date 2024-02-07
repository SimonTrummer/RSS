package at.kaindorf.exa_205rss.database;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

    @Override
    public LocalDate unmarshal(String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return FORMATTER.format(localDate);
    }
}

