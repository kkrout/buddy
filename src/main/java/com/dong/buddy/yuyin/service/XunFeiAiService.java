package com.dong.buddy.yuyin.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dong.buddy.encry.MD5Encry;

@RestController
public class XunFeiAiService
{

    private static final String APPID = "5b32e01c";

    private static final String API_URL = "http://openapi.xfyun.cn/v2/aiui";

    private static final String APP_KEY = "fdf508db9cc744509a460daa9df2dc1b";

    private static final String UUID = "0c1ba62f417259adef9e23ff0d8174ce";

    private static final String TYPE_TEXT = "text";

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/api/xunfei/semantic")
    public String semantic(String callback, String text) throws Exception
    {

        JSONObject param = new JSONObject();
        // 必选项
        param.put("scene", "main");
        param.put("auth_id", UUID);
        param.put("data_type", TYPE_TEXT);
        // param.put("text", "北京今天的天气");

        String curtime = String.valueOf(System.currentTimeMillis() / 1000);

        String paramMime = toMIMECode(param.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Appid", APPID);
        headers.add("X-CurTime", curtime);
        headers.add("X-Param", paramMime);
        headers.add("X-CheckSum", MD5Encry.encryptionMD5(APP_KEY + curtime + paramMime));
        headers.add("Content-Type", "text/plain;charset=utf-8");

        HttpEntity<String> entity = new HttpEntity<String>(text, headers);

        ResponseEntity<String> result = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
        if (StringUtils.isEmpty(callback))
            return result.getBody();
        else
            return callback + "(" + result + ")";
    }

    private static String toMIMECode(String src) throws UnsupportedEncodingException
    {
        String string = new String(Base64.getMimeEncoder().encodeToString(src.getBytes("UTF-8")));
        string = string.replace("\r\n", "");
        return string;
    }

}
