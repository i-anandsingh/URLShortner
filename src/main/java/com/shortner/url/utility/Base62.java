package com.shortner.url.utility;

public class Base62 {
    private static final String ALPHABET =
        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public static String encode(long value) {
        if (value == 0) return String.valueOf(ALPHABET.charAt(0));
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(ALPHABET.charAt((int) (value % BASE)));
            value /= BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String str) {
        long value = 0;
        for (char c : str.toCharArray()) {
            value = value * BASE + ALPHABET.indexOf(c);
        }
        return value;
    }
}