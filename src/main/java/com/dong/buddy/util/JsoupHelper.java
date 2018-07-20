package com.dong.buddy.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHelper
{

    public static List<String> parseHref(String httpUrl) throws IOException
    {

        List<String> list = new ArrayList<>();
        // 返回HTML的站点URL
        // String httpUrl = "https://www.baidu.com/";
        // 创建连接
        Connection conn = Jsoup.connect(httpUrl);

        // 返回文档对象
        Document doc = conn.get();
        // 返回所有的Element
        Elements eles = doc.getAllElements();
        // 遍历所有的文档
        for (Element ele : eles)
        {
            String tagName = ele.tagName();
            if ("a".equalsIgnoreCase(tagName))
            {
                // 链接地址
                String href = ele.attr("abs:href");
                if (href.startsWith(httpUrl) && !href.equals(httpUrl))
                {
                    list.add(href);
                }
            }
        }
        return list;
    }

    public static List<String> parseAllHref(String httpUrl) throws IOException
    {
        List<String> list = new ArrayList<>();
        // 返回HTML的站点URL
        // String httpUrl = "https://www.baidu.com/";
        // 创建连接
        Connection conn = Jsoup.connect(httpUrl);

        // 返回文档对象
        Document doc = conn.get();
        // 返回所有的Element
        Elements eles = doc.getAllElements();
        // 遍历所有的文档
        for (Element ele : eles)
        {
            String tagName = ele.tagName();
            System.out.println(ele);
            if ("a".equalsIgnoreCase(tagName))
            {
                // 链接地址
                String href = ele.attr("abs:href");
                System.out.println(href);
                if ((href.startsWith("http://") || href.startsWith("https://")) && !href.equals(httpUrl))
                {
                    list.add(href);
                    list.addAll(parseAllHref(href));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException
    {
        System.out.println(parseAllHref("https://www.baidu.com/more/"));
    }

}