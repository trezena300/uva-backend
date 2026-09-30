package com.example.piuva.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private ValidationUtil() {
    }

    public static boolean emailValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean senhaForte(String senha) {
        return senha != null && senha.length() >= 6;
    }
}
