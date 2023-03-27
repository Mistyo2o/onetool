package com.onetool.spider.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author: zh
 * @date: 2023/3/27 17:27
 * @description: 生成QQMusic sign加密串
 */
public class QqEncryptUtils {

    //固定加密参数
    private static final String encNonce = "CJBPACrRuNy7";
    private static final String signPrxfix = "zza";
    private static final char[] dir = "0234567890abcdefghijklmnopqrstuvwxyz".toCharArray();


    public static String getSign(String encParams) {
        return signPrxfix + uuidGenerate() + convertToMd5(encNonce + encParams);
    }


    //获取随机数
    private static String uuidGenerate() {
        int minLen = 10;
        int maxLen = 16;
        Random random = new Random(System.currentTimeMillis());
        int ranLen = random.nextInt(maxLen - minLen) + minLen;
        StringBuilder sb = new StringBuilder(ranLen);
        for (int i = 0; i < ranLen; i++) {
            sb.append(dir[random.nextInt(dir.length)]);
        }
        return sb.toString();
    }

    //生成md5串
    private static String convertToMd5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert secretBytes != null;
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
