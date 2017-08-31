package com.gb.common.util;

/**
 * @author gblau
 * @date 2016-11-14
 */
public class SqlSearchUtil {
    public static String getFuzzyQueryString(String value) {
        return "%" + value + "%";
    }

    public static String getSingleCharFuzzyQueryString(String value) {
        return "_" + value + "_";
    }
}
