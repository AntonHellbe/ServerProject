package org.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by Sebastian Börebäck on 2016-04-22.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfigController extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers(
						"/",
						"/index.html",
						"/home.html",
						"/login.html",
						"/app.js",
						"/bootstrap-3.3.6-dist/css/*",
						"/angular-1.5.3/angular.js",
						"/angular-1.5.3/angular-route.js"


				).permitAll()
				.antMatchers("/admin_r").hasAuthority(AuthoritiesConstants.ADMIN)
//				.antMatchers("/users/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/users/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.anyRequest()
				.authenticated()
				.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository())
		;
//
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}