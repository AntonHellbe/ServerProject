package org.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Börebäck on 2016-04-25.
 */
@RestController
public class SecurityController {

	@RequestMapping("/api/account")
	public Principal user(Principal user) {
		System.out.println("got user "+user.getName());
		return user;
	}


	@RequestMapping("/admin_r")
	public Map<String, Object> admin_r() {
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
