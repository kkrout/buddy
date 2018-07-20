package com.dong.buddy.main.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.buddy.IdWorker;

@RestController
public class DemoController
{

    @Autowired
    private RedissonClient client;

    @RequestMapping(value = "/api/say")
    public String say() throws Exception
    {
        IdWorker wo = new IdWorker(2);

        RBucket<String> test = client.getBucket("test");
        test.set("value");

        RBucket<String> test2 = client.getBucket("test");
        System.out.println(test2.get());

        return "Hello";
    }

    private static final String CONTENT_TYPE = "image/jpeg";

    @RequestMapping(value = "/api/draw")
    public void draw(HttpServletResponse response) throws Exception
    {
        try
        {
            response.setHeader("Pragma", "No-cache");

            response.setHeader("Cache-Control", "no-cache");

            response.setContentType(CONTENT_TYPE);

            response.setDateHeader("Expires", 0);

            int windowWidth = 400;
            int windowHeight = 600;

            BufferedImage image = new BufferedImage(windowWidth, windowHeight,

                    BufferedImage.TYPE_INT_RGB);

            OutputStream os = response.getOutputStream();

            Graphics g = image.getGraphics();

            // D:\svn\PurcottonMiniProgram_20180410\pages\res\pyq_bg.png
            BufferedImage img = ImageIO
                    .read(new File("D:\\svn\\PurcottonMiniProgram_20180410\\pages\\res\\pyq_bg.png"));
            g.drawImage(img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH), 0, 0, windowWidth,
                    windowHeight, null);

            int btnWidth = 150, btnHeight = 40;
            int btnX = windowWidth - btnWidth - 10;
            int btnY = 0;
            int goodY = 20;
            // 矩形,马上参与
            g.setColor(new Color(27, 160, 132));
            g.fillRect(btnX, btnY, btnWidth, btnHeight);

            g.setFont(new Font("微软雅黑", Font.BOLD, 18));
            g.setColor(new Color(255, 255, 255));
            g.drawString("马上参与", btnX + 40, btnY + btnHeight / 2 + 5);

            g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            g.setColor(new Color(250, 0, 0));
            g.drawString("团价￥11", 10, goodY);

            goodY += 26;
            g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            g.setColor(new Color(250, 0, 0));
            g.drawString("原价￥22", 10, goodY);

            // Toolkit toolkit;
            // toolkit = Toolkit.getDefaultToolkit();
            // URL url = new URL(
            // "https://res.purcotton.com/images/commodity/107001002/80000000/107000000051/44DB13D8F85B76087DCE8DDD6B66D5DB.jpg");
            // Image image2 = toolkit.getImage(url);
            // g.drawImage(image2, 0, goodY + 10, (int) (windowWidth * 0.7), (int) (windowHeight *
            // 0.7), null);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpPost = new HttpGet(
                    "https://res.purcotton.com/images/commodity/107001002/80000000/107000000051/44DB13D8F85B76087DCE8DDD6B66D5DB.jpg");
            HttpResponse response1 = httpClient.execute(httpPost);
            HttpEntity resEntity = response1.getEntity();
            InputStream inputStream = resEntity.getContent();
            // OutputStream output = new FileOutputStream("D:\\qr\\q.png");
            //
            // byte[] b = new byte[1024];
            // int nRead = 0;
            // while ((nRead = inputStream.read(b)) != -1)
            // {
            // output.write(b, 0, nRead);
            // }
            // output.flush();
            // output.close();
            Image image2 = ImageIO.read(inputStream);

            inputStream.close();

            httpPost.abort();
            //
            // Image image2 = ImageIO.read(new File("D:\\qr\\q.png"));
            g.drawImage(image2, 0, goodY + 10, windowWidth, windowWidth, null);

            g.dispose();

            ImageIO.write(image, "JPEG", os);

            os.flush();

            os.close();

            os = null;

            response.flushBuffer();

            // out.clear();

            // out = pageContext.pushBody();

        }
        catch (IllegalStateException e)
        {

            e.printStackTrace();

        }
    }

    /**
     * 字符串转换成Color对象
     * 
     * @param colorStr
     *            16进制颜色字符串
     * @return Color对象
     */
    public static Color toColorFromString(String colorStr)
    {
        colorStr = colorStr.substring(4);
        Color color = new Color(Integer.parseInt(colorStr, 16));
        // java.awt.Color[r=0,g=0,b=255]
        return color;
    }

}
