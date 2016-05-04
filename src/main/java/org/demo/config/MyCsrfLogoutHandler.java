//package org.demo.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.util.Assert;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by Sebastian Börebäck on 2016-05-04.
// */
//public class MyCsrfLogoutHandler implements LogoutHandler {
//
//	private static final Logger log = LoggerFactory.getLogger(MyCsrfLogoutHandler.class);
//
//	private final CsrfTokenRepository csrfTokenRepository;
//
//	public MyCsrfLogoutHandler(CsrfTokenRepository csrfTokenRepository) {
//		Assert.notNull(csrfTokenRepository, "csrfTokenRepository cannot be null");
//		this.csrfTokenRepository = csrfTokenRepository;
//	}
//
//	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//
//
//		log.info("Doing logout");
//		if (authentication != null) {
//			log.info("user: "+authentication.getName());
//		}
//		this.csrfTokenRepository.saveToken((CsrfToken) null, request, response);
//
//	}
//
//}
