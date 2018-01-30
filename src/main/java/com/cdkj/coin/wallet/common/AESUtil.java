package com.cdkj.coin.wallet.common;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import com.cdkj.coin.wallet.exception.BizException;

public class AESUtil {

    // 加密
    public static String jdkAESEncryption(String src) {
        String result = null;
        try {
            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed("bcoin".getBytes());
            keyGenerator.init(128, random);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(src.getBytes());
            result = Hex.toHexString(encodeResult);
        } catch (Exception e) {
            throw new BizException("AES加密发生异常，原因：" + e.getMessage());
        }
        return result;
    }

    // 解密
    public static String jdkAESDecryption(String src) {
        String result = null;
        try {

            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed("bcoin".getBytes());
            keyGenerator.init(128, random);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(Hex.decode(src));
            result = new String(decodeResult);

        } catch (Exception e) {
            throw new BizException("AES解密发生异常，原因：" + e.getMessage());
        }
        return result;

    }

    public static void main(String[] args) {
        String secretString = jdkAESEncryption("U201711301955544463066");
        System.out.println(secretString);
        System.out.println(jdkAESDecryption(secretString));
    }
}
