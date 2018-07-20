package com.dong.buddy.mongo;

import com.dong.buddy.mongo.dao.MenuParam;
import com.dong.buddy.mongo.entity.MenuEntity;
import com.dong.buddy.mongo.entity.UserEntity;

import java.util.List;

public interface IMenuDao {

    void save(MenuEntity e);

    MenuEntity findById(String id);

    void update(MenuEntity e);

    void delete(String id);

    List<MenuEntity> queryList(MenuParam e);

}
