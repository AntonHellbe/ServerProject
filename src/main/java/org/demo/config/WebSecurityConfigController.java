package org.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
                    //Bara någon med ADMIN kan nå denna
                    .antMatchers("/admin_r").hasAuthority(AuthoritiesConstants.ADMIN)
                    //Bara någon med piRole ska nå denna
                    .antMatchers("/api/pi/{id}").hasAuthority(AuthoritiesConstants.PIUSER)
                    //Alla ska nå denna
                    .antMatchers("/api/users/{id}").permitAll()
                    //Bara någon med ADMIN kan nå dessa (Förutom den ovan)
                    .antMatchers("/api/users/**").hasAuthority(AuthoritiesConstants.ADMIN)
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
                        , "/api/account"
                        , "/logout"
                        , "/app/**"
                        , "/src/**"
                    ).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .logout()
                        //.addLogoutHandler(new CustomLogoutSuccessHandler(), CustomLogoutSuccessHandler.class)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .deleteCookies("XSRF-TOKEN")
                        .permitAll()
                .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository()).disable()


//				// TODO: 2016-04-26 har behover vi dela upp vilka roller som far lov att accessa REST
//				.anyRequest()
//				.authenticated()
//				.and()
//				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//				.csrf().csrfTokenRepository(csrfTokenRepository()).disable()
        ;

    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

}