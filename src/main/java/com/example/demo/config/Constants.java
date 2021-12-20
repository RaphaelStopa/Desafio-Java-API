package com.example.demo.config;

import java.util.Locale;

public final class Constants {

    public static final Locale DEFAULT_SYSTEM_LOCALE = new Locale("pt", "BR");
    // todo tem que ver o pq o logn nao estar filtrando
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String DEFAULT_LANGUAGE = "pt-br";
    public static final String SYSTEM = "system";
    public static final String INTEGRATION = "integration";
    public static final String PASSCODE_HEADER = "X-Auth-Passcode";
    public static final String ATTACHMENT_HASH_HEADER = "X-Attachment-Hash";

    private Constants() {}
}
