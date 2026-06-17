package com.caraveo.gateway.infrastructure.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    private static final String SECRET =
            "1234567890123456";

    public String decrypt(String encryptedText) {

        try {

            SecretKeySpec key =
                    new SecretKeySpec(
                            SECRET.getBytes(),
                            "AES");

            Cipher cipher =
                    Cipher.getInstance(
                            "AES/ECB/PKCS5Padding");

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    key);

            byte[] decoded =
                    Base64.getDecoder()
                            .decode(encryptedText);

            return new String(
                    cipher.doFinal(decoded));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}