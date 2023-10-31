package com.example.structure.member.domain.login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "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"; // 시크릿 키를 적절한 값으로 변경해야 합니다.

    public String createJwt(int user_num, String user_id) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plus(1, ChronoUnit.HOURS)); // JWT의 만료 시간을 설정합니다.

        return Jwts.builder()
                .claim("user_num", user_num)
                .claim("user_id", user_id)
                .setHeaderParam(JwsHeader.KEY_ID, "210138418334-8lps4ikr6147mm361593c8am5b6eekdu.apps.googleusercontent.com") // 필요한 경우 헤더 파라미터 설정 가능
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parseJwt(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt);
    }

    private Key getSigningKey() {
        // 실제 애플리케이션에서는 안전한 방식으로 시크릿 키를 관리해야 합니다.
        byte[] secretBytes = Base64.getDecoder().decode("asdfjp298pafo1320efqw0udvnh0er0h412r08byfdq9dsvh02143908hfeqwn80fdqs0nfdqs0n412013rq0nqefn08dfas0das730732g86923");
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
