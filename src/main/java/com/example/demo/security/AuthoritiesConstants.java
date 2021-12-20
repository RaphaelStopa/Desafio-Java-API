package com.example.demo.security;

public final class AuthoritiesConstants {

    //TODO to see
    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String DIRECTOR = "ROLE_DIRECTOR";

    public static final String MANAGER = "ROLE_MANAGER";

    public static final String GPASP = "ROLE_GPASP";

    public static final String DIRECT_RESPONSIBLE = "ROLE_DIRECT_RESPONSIBLE";

    public static final String[] ALL_AUTHORITIES_ARRAY = { ADMIN, USER, ANONYMOUS, DIRECTOR, MANAGER, GPASP, DIRECT_RESPONSIBLE };
    public static final String[] ALL_AUTHENTICATED_ARRAY = { ADMIN, USER, DIRECTOR, MANAGER, GPASP, DIRECT_RESPONSIBLE };

    private AuthoritiesConstants() {}
}
