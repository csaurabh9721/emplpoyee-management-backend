package com.devix.employemanagement.utils;

public class MaskingUtil {

    private MaskingUtil() {
        // Prevent instantiation
    }

    // 🔹 Generic Masking (Reusable)
    public static String mask(String value, int visibleDigits) {
        if (value == null || value.isEmpty()) return value;

        String clean = value.replaceAll("\\s+", "");

        if (clean.length() <= visibleDigits) return clean;

        int maskedLength = clean.length() - visibleDigits;

        String maskedPart = "X".repeat(maskedLength);
        String visiblePart = clean.substring(maskedLength);

        return maskedPart + visiblePart;
    }

    // 🔹 Aadhar Masking (XXXX XXXX 1234)
    public static String maskAadhar(String aadhar) {
        if (aadhar == null || aadhar.isEmpty()) return aadhar;

        String clean = aadhar.replaceAll("\\s+", "");

        if (clean.length() != 12) return aadhar; // invalid case

        return "XXXX XXXX " + clean.substring(8);
    }

    // 🔹 Bank Account Masking (XXXXXXXXXXXX3456)
    public static String maskBankAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) return accountNumber;

        String clean = accountNumber.replaceAll("\\s+", "");

        if (clean.length() <= 4) return clean;

        int maskedLength = clean.length() - 4;

        return "X".repeat(maskedLength) + clean.substring(maskedLength);
    }

    // 🔹 PAN Masking (ABCDE1234F → ABCDE****F)
    public static String maskPan(String pan) {
        if (pan == null || pan.length() != 10) return pan;

        return pan.substring(0, 5) + "****" + pan.substring(9);
    }

    // 🔹 Mobile Masking (9876543210 → 98XXXXXX10)
    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length() < 10) return mobile;

        return mobile.substring(0, 2)
                + "XXXXXX"
                + mobile.substring(mobile.length() - 2);
    }
}