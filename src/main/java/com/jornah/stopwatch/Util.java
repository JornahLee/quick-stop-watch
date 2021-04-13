package com.jornah.stopwatch;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Util {
    private final static DecimalFormat FORMAT = new DecimalFormat("00.00");

    public static String getFormatTime(long spend) {
        String spendStr;
        if (spend >= TimeUnit.SECONDS.toNanos(1)) {
            spendStr = FORMAT.format(spend / (Math.pow(10, 9) + 0.0)) + " " + TimeUnit.SECONDS.name().toLowerCase();
        } else if (spend >= TimeUnit.MILLISECONDS.toNanos(1)) {
            spendStr = FORMAT.format(spend / (Math.pow(10, 6) + 0.0)) + " " + TimeUnit.MILLISECONDS.name().toLowerCase();
        } else {
            spendStr = FORMAT.format(spend) + " " + TimeUnit.NANOSECONDS.name().toLowerCase();
        }
        return spendStr;
    }
}
