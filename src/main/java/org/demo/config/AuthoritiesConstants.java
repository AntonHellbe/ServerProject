package org.demo.config;

/**
 * Constants for Spring Security authorities.
 * @author Robin Johnsson
 */
public final class AuthoritiesConstants {

	public static final String ADMIN = "ROLE_ADMIN";

	public static final String PIUSER="ROLE_PI";

	public static final String USER = "ROLE_USER";

	public static final String ANONYMOUS = "ROLE_ANONMOUS";

	private AuthoritiesConstants() {
	}
}
