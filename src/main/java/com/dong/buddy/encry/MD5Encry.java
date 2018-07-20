package com.dong.buddy.encry;

import java.security.MessageDigest;

/**
 * MD5即Message Digest Algorithm5(信息摘要发5)，是数字摘要算法的一种实现，用于确保信息传输完整性和一致性，摘要长度为128位。
 * MD5是由MD4、3、2改进而来，主要增强了算法复杂度和不可逆。该算法因其普遍、稳定、快速的特点，被业界广泛使用
 *
 * @author zhdong
 * @version 1.0, 2018年4月20日
 * @since JDK1.8
 */
public class MD5Encry
{

    public static String encryptionMD5(String content)
    {
        try
        {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(content.getBytes());
            return toHex(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String toHex(byte[] bytes)
    {

        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++)
        {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(encryptionMD5(
                "fdf508db9cc744509a460daa9df2dc1b1530064034eyJhdXRoX2lkIjoiMGMxYmE2MmY0MTcyNTlhZGVmOWUyM2ZmMGQ4MTc0Y2UiLCJkYXRhX3R5cGUiOiJ0ZXh0Iiwic2NlbmUiOiJtYWluIn0="));
    }

}
