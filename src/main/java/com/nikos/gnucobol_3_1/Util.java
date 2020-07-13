package com.nikos.gnucobol_3_1;

import java.util.Collection;

public class Util {
    public static String quote(String string) {
        return "'" + string + "'";
    }

    public static String unquote(String string) {
        if ((string.startsWith("\"") && string.endsWith("\"")) || (string.startsWith("'") && string.endsWith("'"))) {
            string = string.substring(1, string.length() - 1);
        }

        return string;
    }

    public static boolean isQuoted(String string) {
        return !Util.unquote(string).equals(string);
    }

    public static String startOverflow(String string, int length) {
        return string.substring(0, string.length() - length);
    }

    public static String endOverflow(String string, int length) {
        return string.substring(length);
    }

    public static String implode(Collection<String> strings, String glue) {
        String result = "";
        for (String string : strings) {
            result += string + glue;
        }

        return result.substring(0, result.length() - glue.length());
    }
}
