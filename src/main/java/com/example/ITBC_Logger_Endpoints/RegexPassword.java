package com.example.ITBC_Logger_Endpoints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPassword {

    private static final String RegexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static Pattern pattern = Pattern.compile(RegexPassword);

    public static boolean isValidPassword(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
