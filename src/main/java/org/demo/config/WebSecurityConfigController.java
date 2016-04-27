package org.demo.config;

import org.springframework.beans.factory.annotation.Value;
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

	@Value ("${management.security.role}")
	private String adminRole;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// TODO: 2016-04-26 httpBasic ska inte vara med. behovs losas po annat vis.
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
				//Bara någon med ADMIN kan nå denna
				.antMatchers("/admin_r").hasAuthority(adminRole)
				//Alla ska nå denna
				.antMatchers("/api/users/{id}").permitAll()
				//Bara någon med ADMIN kan nå dessa (Förutom den ovan)
				.antMatchers("/api/users/**").hasAuthority(adminRole)
				//Bara någon med ADMIN kan nå dessa
				.antMatchers("/api/time/{id}/{stampId}").hasAuthority(adminRole)
				// TODO: 2016-04-26 har behover vi dela upp vilka roller som far lov att accessa REST
				.anyRequest()
				.authenticated()
				.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository())
		;

	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}