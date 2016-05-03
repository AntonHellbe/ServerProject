package org.demo.config;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

//    @Value("${management.security.role}")

	public static String ADMIN = "ROLE_ADMIN";

	public static final String USER = "ROLE_USER";

	public static final String ANONYMOUS = "ROLE_ANONMOUS";

	private AuthoritiesConstants() {
	}
}
