package com.dong.buddy.mongo;

import java.util.Date;

import org.json.JSONObject;

import com.dong.buddy.mongo.entity.BaseEntity;
import com.dong.buddy.mongo.entity.UserEntity;
import com.dong.buddy.util.UUIDUtil;

public abstract class EntityFactory
{

    public static <T extends BaseEntity> T createBaseEntity(Class<T> cls)
    {
        try
        {
            T entity = cls.newInstance();
            Date date = new Date();
            entity.setCreateDate(UUIDUtil.getTimeId());
            entity.setLastUpdateDate(UUIDUtil.getTimeId());
            return entity;
        }
        catch (Exception e)
        {
            // TODO 日志打印
            return null;
        }
    }




    public static void main(String[] args)
    {
        UserEntity user = createBaseEntity(UserEntity.class);
        System.out.println(new JSONObject(user));
    }

}
