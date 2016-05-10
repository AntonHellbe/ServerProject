package org.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Anton on 2016-05-04.
 */
@Component
public class CustomLogoutSuccessHandler implements SessionRepository{

    @Override
    public Session createSession() {
        return null;
    }

    @Override
    public void save(Session session) {

    }

    @Override
    public Session getSession(String s) {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
