package com.dong.buddy.upload;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dong.buddy.util.UUIDUtil;

@RestController
public class ImageUpload
{

    @Autowired
    private RedissonClient client;

    @RequestMapping(value = "/api/image/upload", method = RequestMethod.POST)
    public String upload(String data) throws JSONException
    {
        // String path = saveData(data);
        String timeId = UUIDUtil.getTimeId();
        RMap<Object, Object> map = client.getMap("code_image");
        map.put(timeId, data);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", timeId);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/api/image/all", method = RequestMethod.GET)
    public String allImg() throws JSONException
    {
        RMap<Object, Object> imageMap = client.getMap("code_image");
        Set<Object> keySet = imageMap.keySet();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", keySet);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/api/image/get", method = RequestMethod.GET)
    public String get(String key) throws JSONException, IOException
    {
        RMap<Object, Object> imageMap = client.getMap("code_image");
        return (String) imageMap.get(key);
    }

    @RequestMapping(value = "/api/image/del", method = RequestMethod.POST)
    public String del(String key) throws JSONException, IOException
    {
        RMap<Object, Object> imageMap = client.getMap("code_image");
        imageMap.remove(key);
        return "OK";
    }

    // private String saveData(String data) throws IOException
    // {
    // String path = UUIDUtil.getId();
    // FileUtil.write(data, "D:\\tmp\\resource\\img_" + path);
    // return path;
    // }

}
