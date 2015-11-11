package com.omizil.x.purifier.web.utils;

import java.text.DecimalFormat;

/**
 * @author omizil on 15/11/6.
 */
public class NumberFormatUtils {

    public static String format(Number number) {
        return format(number, "#0.000");
    }

    public static String format(Number number, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }
}
