package com.jiduck.simpleboard.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public PrincipalDetails getPrincipalDetails() {
        return (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
