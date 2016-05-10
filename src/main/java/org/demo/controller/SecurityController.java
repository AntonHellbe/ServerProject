package org.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Börebäck on 2016-04-25.
 */
//@CrossOrigin(origins = "*")
@RestController
public class SecurityController {

	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping("/api/account")
	public Principal user(Principal user) {
		log.info("Doing LOGIN");
		if (user != null) {
			log.info("loggin in user: "+user.getName());
		}
		return user;
	}

	/***
	 * Token RestPoint, for getting the sessionID. instead of logging in
	 * all the time
	 * @param session
	 * @return the session id used to identify you self.
	 */
	@RequestMapping("/token")
	@ResponseBody
	public Map<String,String> token(HttpSession session) {
		log.info("Get token for session "+session.getId());
		return Collections.singletonMap("token", session.getId());
	}


	@RequestMapping("/admin_r")
	public Map<String, Object> admin_r() {
		log.info("you got in");
		Map<String, Object> model = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.put("id", auth.getName());
		model.put("content", "Hello Admin");
		return model;
	}


	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.put("id", auth.getName());
		model.put("content", "Hello World from resource "+ format1.format(Calendar.getInstance().getTime()).toString());
		return model;
	}
}