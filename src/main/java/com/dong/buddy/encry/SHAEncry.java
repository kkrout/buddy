package com.dong.buddy.encry;

import java.security.MessageDigest;

/**
 * SHA的全称是Secure Hash Algorithm，即安全散列算法。1993年美国国家标准和技术协会提出，1995年又发布了一个修订版，
 * 通常称之为SHA-1。SHA-1是基于MD4算法的，现在已成为公认的最安全的散列算法之一，并被广泛使用
 * SHA-1算法生成的摘要信息的长度为160位，由于生成的摘要信息更长，运算的过程更加复杂，在相同的硬件上，SHA-1的运行速度比MD5更慢但是也更安全
 *
 * @author zhdong
 * @version 1.0, 2018年4月20日
 * @since JDK1.8
 */
public class SHAEncry
{

    public static byte[] encryptionSHA1(String content)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(content.getBytes("utf-8"));
            return bytes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
