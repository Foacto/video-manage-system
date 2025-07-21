package com.tuan.vtube.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESEncryption {
    //TODO: convert to key stored in config.properties
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";
    private static final String ALGORITHM = "AES";
    private static final String ALGORITHMS = "PBKDF2WithHmacSHA256";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    public static String encrypt(String strToEncrypt) {
        try {
            // Create default byte array
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory object
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHMS);

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            // Return encrypted string
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {

            // Default byte array
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory Object
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHMS);

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            // Return decrypted string
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.getMessage());
        }
        return null;
    }
}
