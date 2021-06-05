package io.methea.utils.auth;

import io.methea.constant.MetheaConstant;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class RsaKeyGenerate {

    public KeyPair createRsa(int keySize) {

        KeyPair keyPair = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            // Create the public and private keys
            KeyPairGenerator generator = KeyPairGenerator.getInstance(MetheaConstant.RSA, "BC");

            SecureRandom random = new SecureRandom();
            generator.initialize(keySize, random);

            keyPair = generator.generateKeyPair();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return keyPair;
    }

    private SecureRandom createFixedRandom() {
        return new FixedRand();
    }

    private static class FixedRand extends SecureRandom {

        private static final long serialVersionUID = 7296661662982461871L;

        MessageDigest sha;
        byte[] state;

        FixedRand() {
            try {
                this.sha = MessageDigest.getInstance("SHA-512");
                this.state = sha.digest();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("can't find SHA-512!");
            }
        }

        public void nextBytes(byte[] bytes) {
            int off = 0;
            sha.update(state);
            while (off < bytes.length) {
                state = sha.digest();
                System.arraycopy(state, 0, bytes, off, Math.min(bytes.length - off, state.length));
                off += state.length;
                sha.update(state);
            }
        }
    }
}
