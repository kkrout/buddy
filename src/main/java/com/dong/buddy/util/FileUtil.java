package com.dong.buddy.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件工具类
 * 读写文件之类的操作
 */

public class FileUtil
{

    public static void write(String str, String path) throws IOException
    {
        write(str, path, false);
    }

    public static void write(String str, String path, boolean append) throws IOException
    {
        try (OutputStream out = new FileOutputStream(path, append))
        {
            out.write(str.getBytes());
        }
    }

    public static void append(String str, String path) throws IOException
    {
        write(str, path, true);
    }

}
