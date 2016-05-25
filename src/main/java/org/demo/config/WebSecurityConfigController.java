package org.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by Sebastian Börebäck on 2016-04-22.
 * @author Sebastian Börebäck, Robin Johnsson
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
				//Bara någon med ADMIN kan nå denna
				.antMatchers("/api/account").hasAnyAuthority(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER, AuthoritiesConstants.PIUSER)
				.antMatchers("/admin_r").hasAuthority(AuthoritiesConstants.ADMIN)
				//Bara någon med piRole ska nå denna
				.antMatchers("/api/pi/{id}").hasAuthority(AuthoritiesConstants.PIUSER)
				//Alla ska nå denna
				.antMatchers("/api/users/{id}").hasAuthority(AuthoritiesConstants.USER)
				//Bara någon med ADMIN kan nå dessa (Förutom den ovan)
				.antMatchers("/api/users","/api/users/**").hasAuthority(AuthoritiesConstants.ADMIN)
				//Bara någon med ADMIN kan nå dessa
				.antMatchers("/api/time/{id}/{stampId}").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers(
						"/",
						"/index.html",
						"/home.html",
						"/login.html",
						"/app.js",
						"/bootstrap-3.3.6-dist/css/*",
						"/angular-1.5.3/angular.js",
						"/angular-1.5.3/angular-route.js"
						, "/bower_components/**"
						, "/app/**"
						, "/src/**"
						,"/wsservice/**"
						,"/images/**"
				).permitAll()
					.anyRequest()
					.authenticated()
				.and()
				.logout()
				//after logout send user to login screen
				.logoutSuccessUrl("/#!/login")
				//custom logouthandler with cookies
				.addLogoutHandler(new MyLogoutHandler("JSESSIONID"))

				.and()
				// TODO: 2016-05-04 :22:21 CSRF fungerare inte med olika enheter...
					.csrf()
						.disable() //sa att man far lova att logga ut

		;

	}

}