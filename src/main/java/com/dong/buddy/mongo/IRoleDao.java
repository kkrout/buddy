package com.dong.buddy.mongo;

import com.dong.buddy.mongo.dao.MenuParam;
import com.dong.buddy.mongo.entity.RoleEntity;

import java.util.List;

public interface IRoleDao {

    void save(RoleEntity e);

    RoleEntity findById(String id);

    void update(RoleEntity e);

    void delete(String id);

    List<RoleEntity> queryList(MenuParam e);

}
