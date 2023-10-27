package com.example.securityinaction.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginFail implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e){
        try{

            httpServletResponse.setHeader("failed", LocalDateTime.now().toString());
            httpServletResponse.sendRedirect("/error");
        }catch (IOException ioException){
            throw new RuntimeException(ioException);
        }
    }
}
