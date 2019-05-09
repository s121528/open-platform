package com.io.sdk.util;

import jodd.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/24 时间:13:51
 * @JDK 1.8
 * @Description 功能模块：
 */
public class DESUtil {
    private static Map<String, String> keyMap = new HashMap<>();  //用于封装随机产生的公钥与私钥
    // 密钥 ,至少24位
    private final static String secretKey = "tjhj0xtvaqjicdu9os2agyu8";
    // 向量 8位
    private final static String iv = "3213abcd";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * DESTool加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encodeToString(encryptData);
    }

    /**
     * DESTool解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String secretKey, String iv) throws Exception {
        Key deskey;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
        return new String(decryptData, encoding);
    }

    public static void main(String[] args) throws Exception {
        //
        String decode = DESUtil.decode("qxK3k+qRygTKm66EjGAzESHtDFQ+fhri6Zei0F3J3Wl/h64Lo4XsJVvb6VIJpFxc", secretKey, iv);
        System.out.println(decode);
    }
}
