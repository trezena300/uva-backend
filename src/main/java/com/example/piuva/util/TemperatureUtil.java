package com.example.piuva.util;

public class TemperatureUtil {

    private static final double TEMPERATURA_MAXIMA_IDEAL = 32.0;
    private static final double TEMPERATURA_MINIMA_IDEAL = 15.0;
    private static final double UMIDADE_MAXIMA_IDEAL = 70.0;

    private TemperatureUtil() {
    }

    public static boolean temperaturaForaDoIdeal(double temperatura) {
        return temperatura > TEMPERATURA_MAXIMA_IDEAL || temperatura < TEMPERATURA_MINIMA_IDEAL;
    }

    public static boolean umidadeForaDoIdeal(double umidade) {
        return umidade > UMIDADE_MAXIMA_IDEAL;
    }

    public static double celsiusParaFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}
