package com.dong.buddy.encry;

/**
 * 计算机的计算采用是二进制的数据表示方法，而十六进制也是数据的一种表示方法，并且可以与二进制表示法不同的是，十六进制由0~9和A~F来进行表示，
 * 与十进制的对应关系是：0~9对应，A~F对应10~15。Java的十六进制编码与解码的实现
 * 每一个byte包含8位二进制数据，由于Java中没有无符号整型，因此8位中有一位为符号位，需要将符号位转换为对应的数值，
 * 然后在转换为对应的十六进制。8位二进制可以转换为2位十六进制，不足2位的进行补0，而解码时，需要先将符号位进行还原，
 * 再对数值进行转换，使用了Integer.parseInt(subStr,16)这个方法来对十六进制进行解析，将其转换为整型的数值，然后判断正负，计算出符号位，
 * 并将剩余的位还原为byte的数值
 * 
 * @author zhdong
 * @version 1.0, 2018年4月20日
 * @since JDK1.8
 */
public class HexEncry
{

    public static String bytes2hex(byte[] bytes)
    {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            byte b = bytes[i];
            boolean negative = false;// 是否为负数
            if (b < 0)
                negative = true;
            int inte = Math.abs(b);
            if (negative)
                inte = inte | 0x80;
            // 负数会转成整数(最高位的负号变成数值计算)，在转十六进制
            String temp = Integer.toHexString(inte & 0xFF);
            if (temp.length() == 1)
            {
                hex.append("0");
            }
            hex.append(temp.toLowerCase());
        }
        return hex.toString();
    }

    public static byte[] hex2bytes(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i = i + 2)
        {
            String subStr = hex.substring(i, i + 2);
            boolean negative = false;// 是否为负数
            int inte = Integer.parseInt(subStr, 16);
            if (inte > 127)
                negative = true;
            if (inte == 128)
            {
                inte = -128;
            }
            else if (negative)
            {
                inte = 0 - (inte & 0x7F);
            }
            byte b = (byte) inte;
            bytes[i / 2] = b;
        }
        return bytes;
    }
}
