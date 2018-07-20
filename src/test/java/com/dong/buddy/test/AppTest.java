// package com.dong.buddy.test;
//
// import java.io.UnsupportedEncodingException;
// import java.util.Base64;
//
// import org.json.JSONObject;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.web.client.RestTemplate;
//
// import com.dong.buddy.encry.MD5Encry;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class AppTest
// {
//
// private static final String APPID = "5b32e01c";
//
// private static final String API_URL = "http://openapi.xfyun.cn/v2/aiui";
//
// private static final String APP_KEY = "fdf508db9cc744509a460daa9df2dc1b";
//
// private static final String UUID = "0c1ba62f417259adef9e23ff0d8174ce";
//
// private static final String TYPE_TEXT = "text";
//
// @Autowired
// private RestTemplate restTemplate;
//
// @Autowired
// private ObjectMapper jsonMapper;
//
// @Test
// public void contextLoads() throws Exception
// {
// JSONObject param = new JSONObject();
// // 必选项
// param.put("scene", "main");
// param.put("auth_id", UUID);
// param.put("data_type", TYPE_TEXT);
// param.put("text", "你好啊");
//
// String curtime = String.valueOf(System.currentTimeMillis() / 1000);
//
// String paramMime = toMIMECode(param.toString());
// HttpHeaders headers = new HttpHeaders();
// headers.add("X-Appid", APPID);
// headers.add("X-CurTime", curtime);
// headers.add("X-Param", paramMime);
// headers.add("X-CheckSum", MD5Encry.encryptionMD5(APP_KEY + curtime + paramMime));
//
// HttpEntity<String> entity = new HttpEntity<String>(param.toString(), headers);
//
// ResponseEntity<String> exchange = restTemplate.exchange(API_URL, HttpMethod.POST, entity,
// String.class);
// System.out.println(exchange.getBody());
// }
//
// private static String toMIMECode(String src) throws UnsupportedEncodingException
// {
// return new String(Base64.getMimeEncoder().encodeToString(src.getBytes("UTF-8")));
// }
//
// }
