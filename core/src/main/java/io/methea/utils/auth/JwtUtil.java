package io.methea.utils.auth;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import io.methea.constant.MConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class JwtUtil {

    private static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private JwtUtil(){}

    public static String decodeToken(String token, String privateKey) {
        String username = StringUtils.EMPTY;

        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] data = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance(MConstant.RSA);
            RSAPrivateKey priKey = (RSAPrivateKey) fact.generatePrivate(spec);
            EncryptedJWT jwtDecrypt = EncryptedJWT.parse(new String(Base64.decodeBase64(token)));
            RSADecrypter decrypter = new RSADecrypter(priKey);
            jwtDecrypt.decrypt(decrypter);
            username = jwtDecrypt.getJWTClaimsSet().getSubject();
        } catch (Exception ex) {
            log.error("=========> decodeToken error: ", ex);
        }

        return username;
    }

    public static Map<String, String> encodeToken(String key, String subject, String issuer, Calendar expiration) {

        Map<String, String> map = new HashMap<>();
        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] data = Base64.decodeBase64(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec (data);
            KeyFactory fact = KeyFactory.getInstance(MConstant.RSA);
            RSAPublicKey pubKey = (RSAPublicKey) fact.generatePublic(spec);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(subject)
                    .issuer(issuer)
                    .expirationTime(expiration.getTime())
                    .notBeforeTime(new Date())
                    .issueTime(new Date())
                    .jwtID(UUID.randomUUID().toString())
                    .build();
            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256
                    , EncryptionMethod.A256GCM);
            EncryptedJWT jwtEncrypt = new EncryptedJWT(header, claimsSet);
            RSAEncrypter encryptors = new RSAEncrypter(pubKey);

            jwtEncrypt.encrypt(encryptors);
            String jwtToken = jwtEncrypt.serialize();

            map.put(MConstant.JWT_TOKEN, Base64.encodeBase64String(jwtToken.getBytes()));
        } catch (Exception e) {
            log.error("=========> encodeToken error: " + e);
        }
        return map;
    }
}
