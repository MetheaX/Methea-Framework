package io.github.metheax.utils.auth;

import io.github.metheax.constant.MetheaConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Author : Kuylim Tith
 * Date : 03/05/2020
 */
public class Encryption {

    private Encryption(){}

    public static String encrypt(String text, RSAPublicKey publicKey) throws Exception {
        Cipher c = Cipher.getInstance(MetheaConstant.RSA);
        c.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] input = text.getBytes();
        c.update(input);
        byte[] encVal = c.doFinal();
        return Base64.encodeBase64String(encVal);
    }


    public static String decrypt(String encVal, RSAPrivateKey privateKey) throws Exception {
        Cipher c = Cipher.getInstance(MetheaConstant.RSA);
        c.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decodedValue = Base64.decodeBase64(encVal);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }

    public static String generateRandomPassword(int len, int randNumOrigin, int randNumBound) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange(randNumOrigin, randNumBound)
                .filteredBy(CharacterPredicates.ASCII_ALPHA_NUMERALS)
                .build();
        return generator.generate(len);
    }
}
