package com.ecommerce.repositories;

import com.ecommerce.entities.BlacklistedToken;

import java.time.LocalDateTime;

public interface BlackListedTokenRepository extends BaseRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);
    void deleteAllByExpiresAtBefore(LocalDateTime dateTime);

}
