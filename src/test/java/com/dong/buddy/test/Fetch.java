package com.dong.buddy.test;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dong.buddy.encry.Base64Encry;

public class Fetch
{

    private static String address = "aHR0cHM6Ly85OW5uMy5jb20v";

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String url = Base64Encry.decode(address);
        RestTemplate rst = new RestTemplate();
        ResponseEntity<String> postForEntity = rst.getForEntity(url, null, String.class);
        System.out.println(postForEntity.getBody());
    }

}
