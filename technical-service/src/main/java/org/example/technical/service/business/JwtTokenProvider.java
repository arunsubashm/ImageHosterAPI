package org.example.technical.service.business;

import java.io.UnsupportedEncodingException;
import java.rmi.UnexpectedException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
//import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
//import org.example.technical.service.common.UnexpectedException;
//import org.example.technical.service.common.GenericErrorCode;


public class JwtTokenProvider {
    private static final String TOKEN_ISSUER = "https://imagehoster.io";

    private final Algorithm algorithm;

    public JwtTokenProvider(final String secret) {
        algorithm = Algorithm.HMAC512(secret);
    }

    public String generateToken(final String userUuid, final ZonedDateTime issuedDateTime, final ZonedDateTime expiresDateTime) {

        final Date issuedAt = new Date(issuedDateTime.getLong(ChronoField.INSTANT_SECONDS));
        final Date expiresAt = new Date(expiresDateTime.getLong(ChronoField.INSTANT_SECONDS));

        return JWT.create().withIssuer(TOKEN_ISSUER) //
                .withKeyId(UUID.randomUUID().toString())
                .withAudience(userUuid) //
                .withIssuedAt(issuedAt).withExpiresAt(expiresAt).sign(algorithm);
    }

}
