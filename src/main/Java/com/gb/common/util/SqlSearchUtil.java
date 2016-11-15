package com.gb.common.util;

/**
 * Created by gblau on 2016-11-14.
 */
public class SqlSearchUtil {
    public static String getFuzzyQueryString(String value) {
        return "%" + value + "%";
    }

    public static String getSingleCharFuzzyQueryString(String value) {
        return "_" + value + "_";
    }
}
