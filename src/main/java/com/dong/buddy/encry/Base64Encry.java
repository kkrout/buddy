package com.dong.buddy.encry;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Encry
{

    public static String decode(String str) throws UnsupportedEncodingException
    {
        Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str.getBytes()), "UTF-8");
    }

    public static String toMIMECode(String src) throws UnsupportedEncodingException
    {
        String string = new String(Base64.getMimeEncoder().encodeToString(src.getBytes("UTF-8")));
        string = string.replace("\r\n", "");
        return string;
    }

    public static String parseMIMECode(String src) throws UnsupportedEncodingException
    {
        String string = new String(Base64.getMimeDecoder().decode(src), "UTF-8");
        string = string.replace("\r\n", "");
        return string;
    }

    public static String encoder(String str) throws UnsupportedEncodingException
    {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes());
    }

    public static void main(String[] args) throws Exception
    {
        // String miwen = encoder("1234");
        // System.out.println(miwen);
        // String minwen = decode(miwen);
        // System.out.println(minwen);
        // String str1 = str.substring(0, str.length() / 2);
        // System.out.println(str1);
        // String str2 = str.substring(str.length() / 2, str.length());
        // System.out.println(str2);
        // long l1 = Long.parseLong(str1);
        // long l2 = Long.parseLong(str2);
        // System.out.println(Long.toHexString(l1).length());
        // System.out.println(Long.toHexString(l2).length());

        // 2*9,2*9
        // String beiBas = "0123456789";
        // String bas = "qwertyuiopasdfghjklzxcvbnm";
        // String str = "15238500463289244550000000000000";
        // char[] chars = new char[16];
        // for (int i = 0; i < str.length() / 2; i++)
        // {
        // String sub_ = str.substring(i * 2, (i + 1) * 2);
        // String a = sub_.substring(0, 1);
        // String b = sub_.substring(1, 2);
        // int a_ = Integer.parseInt(a);
        // int b_ = Integer.parseInt(b);
        // System.out.println(a_ + ":" + b_);
        // if (a_ == b_)
        // {
        // chars[i] = beiBas.charAt(a_);
        // }
        // else if (a_ > b_)
        // {
        // chars[i] = bas.charAt((a_ + b_ - 1) / 2);
        // }
        // else if (a_ < b_)
        // {
        // chars[i] = bas.charAt((a_ + b_ - 1) / 2 + 8);
        // }
        //
        // }
        //
        // for (int i = 0; i < str.length() / 2; i++)
        // {
        //
        // }
        // Set<Integer> sit = new HashSet<>();
        // int c = 0;
        // Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        // for (int i = 0; i < 10; i++)
        // {
        //
        // for (int j = 0; j < 10; j++)
        // {
        // String key = String.valueOf(i + j);
        // c++;
        // Set<String> set = map.get(key);
        // if (set == null)
        // {
        // set = new HashSet<>();
        // map.put(key, set);
        // }
        // set.add(i + ":" + j);
        //
        // }
        // }
        // Set<String> keySet = map.keySet();
        // Iterator<String> iterator = keySet.iterator();
        // int sum = 0;
        // while (iterator.hasNext())
        // {
        // String next = iterator.next();
        // Set<String> set = map.get(next);
        // System.out.println("KEY:" + next);
        // System.out.println("\t" + set);
        // sum += set.size();
        // }
        // System.out.println(sum);
        // String bas =
        // "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!#$&'()*+,/:;=?@-._~";
        // String str = "15238500463289244550000000000000";
        // String str1 = str.substring(0, str.length() / 2);
        // System.out.println(str1);
        // String str2 = str.substring(str.length() / 2, str.length());
        // System.out.println(str2);
        // long l1 = Long.parseLong(str1);
        // long l2 = Long.parseLong(str2);
        //
        // char[] chars = new char[16];
        // for (int i = 0; i < str.length() / 2; i++)
        // {
        // String sub_ = str.substring(i * 2, (i + 1) * 2);
        // String a = sub_.substring(0, 1);
        // String b = sub_.substring(1, 2);
        // int a_ = Integer.parseInt(a);
        // int b_ = Integer.parseInt(b);
        // System.out.println(a_ + ":" + b_);
        // if (a_ == b_)
        // {
        // chars[i] = beiBas.charAt(a_);
        // }
        // else if (a_ > b_)
        // {
        // chars[i] = bas.charAt((a_ + b_ - 1) / 2);
        // }
        // else if (a_ < b_)
        // {
        // chars[i] = bas.charAt((a_ + b_ - 1) / 2 + 8);
        // }
        //
        // }
    }

}
