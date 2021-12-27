package com.example.demo.config;

import java.util.Locale;

public final class Constants {

    public static final Locale DEFAULT_SYSTEM_LOCALE = new Locale("pt", "BR");
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    private Constants() {}
}
