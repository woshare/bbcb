package com.example.bbcb.util;

import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CustomDataEncodeUtil {
    private static String key = "bibicibabibicibatest";//key 长度一定要128bit

    public static String encode(String data) throws CustomException {
        return aesEncrypt(data, key);
    }

    public static String decode(String data) throws CustomException {
        return aesDecrypt(data, key);
    }

    /**
     * 加密 AES 对称加解密 Advanced Encryption Standard
     *
     * @return 加密后二进制结果
     * @Param data 加密数据
     * @Param key 秘钥
     */
    private static String aesEncrypt(String data, String key) throws CustomException {
        try {
            byte[] bytesKey = key.getBytes("UTF-8");
            if (bytesKey.length != 16) {
                throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.EncryptedKeyError);
            }
            SecretKey secretKey = new SecretKeySpec(bytesKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64Utils.encodeToString(encrypted);
        } catch (Exception e) {
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.EncryptedFailed, e.getMessage());
        }
    }

    /**
     * 解密
     *
     * @return 解密后结果
     * @Param encrypted 待解密数据
     * @Param key 秘钥
     */
    private static String aesDecrypt(String encrypted, String key) throws CustomException {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64Utils.decodeFromString(encrypted));

            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.DecryptedFailed, e.getMessage());
        }
    }

    private static String md5(String data,String perfix,String suffix) {
        String valueMD5 = null;
        if(StringUtils.isEmpty(data)){
            return valueMD5;
        }
        //加上前缀后缀混淆
        String value=new StringBuilder(perfix).append(data).append(suffix).toString();
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(value.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            valueMD5 = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.MD5Failed, e.getMessage());
        }
        return valueMD5;
    }
}
