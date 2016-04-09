package org.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sebastian Börebäck on 2016-04-09.
 */

/**
 * A webcontroller that handles displaying webpages
 * has url mappings for the diffrent web interfaces
 */
@Controller
public class WebController {

	/***
	 * Main web page request.
	 * when you go to:
	 *localhost:8080
	 * you will get
	 * The index.html in resources/static/index.html
	 * @return the main web page
	 */
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String homepage(){
		return "index.html";
	}

	/**
	 * Connecting Android web interface to
	 * localhost:8080/andw
	 * @return the android web interface
	 */
	@RequestMapping(value="/andw",method = RequestMethod.GET)
	public String androidWeb(){
		return "/andweb/index.html";
	}


	/**
	 * Connecting Item web interface to
	 * localhost:8080/iweb
	 * @return the item test web interface
	 */
	@RequestMapping(value="/iweb",method = RequestMethod.GET)
	public String itemWeb(){
		return "/itemweb/index.html";
	}

	/**
	 * Connecting Item web interface to
	 * localhost:8080/piw
	 * @return the item test web interface
	 */
	@RequestMapping(value="/piw",method = RequestMethod.GET)
	public String piWeb(){
		return "/piweb/index.html";
	}
}
