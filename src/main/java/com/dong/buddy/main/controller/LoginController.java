package com.dong.buddy.main.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.buddy.encry.MD5Encry;
import com.dong.buddy.exception.CommonException;

@RestController
public class LoginController
{

    @RequestMapping(value = "/login")
    public String login(String u, String p, String callback) throws Exception
    {

        if (StringUtils.isEmpty(u) && StringUtils.isEmpty(p))
        {
            throw new CommonException("用户名或者密码为空", "sys.00002");
        }

        String pwdEncode = MD5Encry.encryptionMD5(p);
        if (!pwdEncode.equals("0192023a7bbd73250516f069df18b500"))
        {
            throw new CommonException("用户名或者密码错误", "sys.00003");
        }
        return "SUCCESS";
    }

}
