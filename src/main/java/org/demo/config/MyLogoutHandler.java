package org.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-05-04.
 * @author Sebastian Börebäck
 * Handles logging out
 */
public class MyLogoutHandler implements LogoutHandler {

	private final List<String> cookiesToClear;

	private static final Logger log = LoggerFactory.getLogger(MyLogoutHandler.class);

	public MyLogoutHandler(String... cookiesToClear) {
		Assert.notNull(cookiesToClear, "List of cookies cannot be null");
		this.cookiesToClear = Arrays.asList(cookiesToClear);
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("doing logout ");
		if (authentication != null) {
			log.info("Loggin out user> "+ authentication.getName());
		}

		Iterator itr = this.cookiesToClear.iterator();

		while (itr.hasNext()) {
			String cookieName = (String) itr.next();
			Cookie cookie = new Cookie(cookieName, (String) null);
			String cookiePath = request.getContextPath();
			if (!StringUtils.hasLength(cookiePath)) {
				cookiePath = "/";
			}

			cookie.setPath(cookiePath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

}
