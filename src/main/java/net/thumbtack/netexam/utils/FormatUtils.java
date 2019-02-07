package net.thumbtack.netexam.utils;

import java.text.SimpleDateFormat;

public class FormatUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat getFormat() {
        return format;
    }
}
