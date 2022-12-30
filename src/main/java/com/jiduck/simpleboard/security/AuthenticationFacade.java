package com.jiduck.simpleboard.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();

    PrincipalDetails getPrincipalDetails();
}
