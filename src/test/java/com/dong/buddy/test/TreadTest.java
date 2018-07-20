package com.dong.buddy.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TreadTest
{

    public static void main(String[] args) throws Exception
    {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 50; i++)
        {
            Runnable task = new Runnable()
            {
                public void run()
                {
                    try
                    {
                        httpsRequest("http://localhost:18888/buddy/api/redssion/notify/locker", "GET", null);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            newFixedThreadPool.execute(task);
        }
        newFixedThreadPool.shutdown();

        // String str = httpsRequest2("https://aweme.snssdk.com/aweme/v1/feed/", "GET", "");
        //
        // System.out.println(str);

    }

    public static void httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception
    {

        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置请求方式（GET/POST）
        conn.setRequestMethod(requestMethod);

        // 当outputStr不为null时向输出流写数据
        if (null != outputStr)
        {
            OutputStream outputStream = conn.getOutputStream();
            // 注意编码格式
            outputStream.write(outputStr.getBytes("UTF-8"));
            outputStream.close();
        }

        // 从输入流读取返回内容
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null)
        {
            buffer.append(str);
        }

        // 释放资源
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        conn.disconnect();
        System.out.println(buffer.toString());
    }

    /*
     * 处理https GET/POST请求
     * 请求地址、请求方法、参数
     */
    public static String httpsRequest2(String requestUrl, String requestMethod, String outputStr)
    {
        StringBuffer buffer = null;
        try
        {
            // 创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm =
            {
                    new MyX509TrustManager()
            };
            // 初始化
            sslContext.init(null, tm, new java.security.SecureRandom());
            ;
            // 获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            // 设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            // 往服务器端写内容
            if (null != outputStr)
            {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null)
            {
                buffer.append(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
