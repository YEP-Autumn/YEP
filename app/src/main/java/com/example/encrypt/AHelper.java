package com.example.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

public class AHelper {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String toContent(String key, String content) {

        try {
            System.out.println(key);
            key = Slat.slatKey(key).substring(16);
            System.out.println(key);
            byte[] decode = A.decode(key.getBytes(StandardCharsets.UTF_8), Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8)));
            return new String(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toSecret(String key, String content) {
        try {
            key = Slat.slatKey(key).substring(16);
            byte[] encode = A.encode(key.getBytes(StandardCharsets.UTF_8), content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
