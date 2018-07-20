package com.dong.buddy.mongo;

import com.dong.buddy.mongo.entity.UserEntity;

public interface IUserDao
{

    void saveUser(UserEntity user);

    UserEntity findUserByUserName(String userName);

    void updateUser(UserEntity user);

    void deleteUserById(Long id);

}
