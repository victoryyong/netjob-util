package com.util.object;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JWTUtil {
	public static String createJWT(String id, String issuer, String subject,
			Date expires, String key) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,
				signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setExpiration(expires)
				.setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		return builder.compact();
	}

	public static Claims parseJWT(String jwt, String key) throws Exception {
		Claims claims =

		(Claims) Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(key))
				.parseClaimsJws(jwt).getBody();
		return claims;
	}
}
