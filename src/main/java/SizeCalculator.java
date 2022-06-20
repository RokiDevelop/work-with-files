package main.java;

import java.io.File;
import java.util.Locale;

import static java.lang.Float.parseFloat;

public class SizeCalculator {
    public static final String[] SIGN = {"B", "Kb", "Mb", "Gb", "Tb"};
    public static final float POWER_FACTOR = (float) Math.pow(2, 10);

    public static String getHumanReadableSize(long size) throws IllegalArgumentException {
        float powerFactor = (float) Math.pow(2, 10);
        for (int i = 4; i >= 0; i--) {
            double resultSize = size / Math.pow(powerFactor, i);
            if (resultSize > 0.5) {
                return String.format("%.2f%s", resultSize, SIGN[i]);
            }
        }
        throw new IllegalArgumentException();
    }

    public static long getSizeFromHumanReadable(String size) throws IllegalArgumentException {
        if (size.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String inputSize = size
                .replaceAll("[^0-9.]", "")
                .replaceAll(",", ".");

        float result = parseFloat(inputSize);

        switch (size.toUpperCase(Locale.ROOT).replaceAll("[^A-Z]", "")) {
            case ("TB"):
            case ("T"):
                return (long) (result * Math.pow(POWER_FACTOR, 4));
            case ("GB"):
            case ("G"):
                return (long) (result * Math.pow(POWER_FACTOR, 3));
            case ("MB"):
            case ("M"):
                return (long) (result * Math.pow(POWER_FACTOR, 2));
            case ("KB"):
            case ("K"):
                return (long) (result * Math.pow(POWER_FACTOR, 1));
            case ("B"):
                return (long) result;
        }

        throw new IllegalArgumentException();
    }
}
