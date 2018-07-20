package com.dong.buddy.mongo.entity;

public class UserEntity extends BaseEntity
{

    /**
     * [简要描述]:
     * 
     * @author zhdong
     */
    private static final long serialVersionUID = 8098759179838533845L;

    private String userName;
    private String passWord;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

}
