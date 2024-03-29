package io.github.metheax.utils.auth;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import io.github.metheax.constant.MetheaConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * Author : Kuylim Tith
 * Date : 03/05/2020
 */
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private JwtUtil() {
    }

    public static String decodeToken(String token, PrivateKey privateKey) {
        String subject = StringUtils.EMPTY;

        try {
            EncryptedJWT jwtDecrypt = EncryptedJWT.parse(new String(Base64.decodeBase64(token)));
            RSADecrypter decrypter = new RSADecrypter(privateKey);
            jwtDecrypt.decrypt(decrypter);
            if (System.currentTimeMillis() > jwtDecrypt.getJWTClaimsSet().getExpirationTime().getTime()) {
                return subject;
            }
            subject = jwtDecrypt.getJWTClaimsSet().getSubject();
        } catch (Exception ex) {
            log.error("=========> decodeToken error: ", ex);
        }

        return subject;
    }

    public static Map<String, String> encodeToken(PublicKey key, String subject, String issuer, Calendar expiration) {

        Map<String, String> map = new HashMap<>();
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(subject.concat(MetheaConstant.COLON).concat(RequestContextHolder.currentRequestAttributes().getSessionId()))
                    .issuer(issuer)
                    .expirationTime(expiration.getTime())
                    .notBeforeTime(new Date())
                    .issueTime(new Date())
                    .jwtID(UUID.randomUUID().toString())
                    .build();
            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256
                    , EncryptionMethod.A256GCM);
            EncryptedJWT jwtEncrypt = new EncryptedJWT(header, claimsSet);
            RSAEncrypter encryptors = new RSAEncrypter((RSAPublicKey) key);

            jwtEncrypt.encrypt(encryptors);
            String jwtToken = jwtEncrypt.serialize();

            map.put(MetheaConstant.JWT_TOKEN, Base64.encodeBase64String(jwtToken.getBytes()));
        } catch (Exception e) {
            log.error("=========> encodeToken error: ", e);
        }
        return map;
    }
}
