package com.dong.buddy.mongo.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.buddy.mongo.EntityFactory;
import com.dong.buddy.mongo.IUserDao;
import com.dong.buddy.mongo.entity.UserEntity;

@RestController
public class MongoDemoController
{

    // @Autowired
    private IUserDao userDao;

    @RequestMapping(value = "/api/mongo/demo")
    public String demo() throws Exception
    {
        UserEntity user = EntityFactory.createBaseEntity(UserEntity.class);
        user.setUserName("zhdong");
        user.setPassWord("12346");
        userDao.saveUser(user);

        UserEntity userFromDb = userDao.findUserByUserName("zhdong");

        JSONObject j = new JSONObject(userFromDb);
        return j.toString();
    }

    // @RequestMapping(value = "/api/mongo/run")
    // public String run() throws Exception
    // {
    //
    // }

}
