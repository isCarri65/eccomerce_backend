package com.ecommerce.services;

import com.ecommerce.entities.BlacklistedToken;
import com.ecommerce.repositories.BlackListedTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenBlackListService {
    private final BlackListedTokenRepository repository;

    public TokenBlackListService(BlackListedTokenRepository repository) {
        this.repository = repository;
    }

    public void blacklistToken(String token, LocalDateTime secondsToLive) {
        if (!repository.existsByToken(token)) {
            BlacklistedToken blacklistedToken = new BlacklistedToken();
            blacklistedToken.setToken(token);
            blacklistedToken.setExpiresAt(secondsToLive);
            repository.save(blacklistedToken);
        }
    }
    public boolean isTokenBlacklisted(String token) {
        return repository.existsByToken(token);
    }
    @Scheduled(cron = "0 0 2 * * *") // Todos los días a las 2:00 AM
    public void cleanExpiredTokens() {
        repository.deleteAllByExpiresAtBefore(LocalDateTime.now());
    }

}