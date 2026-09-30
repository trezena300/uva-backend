package com.example.piuva.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private DateUtil() {
    }

    public static String formatar(LocalDateTime data) {
        if (data == null) {
            return null;
        }
        return data.format(FORMATO_BR);
    }

    public static LocalDateTime agora() {
        return LocalDateTime.now();
    }
}
