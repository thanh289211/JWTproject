package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    @Autowired
    private final TokenRepository tokenRepository;
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
    {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);
        var storeToken = tokenRepository.findByToken(jwt).orElse(null);
        if (storeToken != null){
            storeToken.setExpired(true);
            storeToken.setRevoked(true);
            tokenRepository.save(storeToken);
            SecurityContextHolder.clearContext();
        }
    }
}
