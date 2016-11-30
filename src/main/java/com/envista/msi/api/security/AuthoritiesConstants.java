package com.envista.msi.api.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    
    public static final String MSI_UI = "ROLE_CLIENT";
    
    public static final String TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

	public static final long ADMIN_USER_ROLE_ID = 0;

    private AuthoritiesConstants() {
    }
}
