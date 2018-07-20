package com.dong.buddy.mongo.dao;

import com.dong.buddy.mongo.IRoleDao;
import com.dong.buddy.mongo.entity.MenuEntity;
import com.dong.buddy.mongo.entity.RoleEntity;
import com.dong.buddy.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RoleDao implements IRoleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(RoleEntity e) {
        Long nextId = UUIDUtil.getNextId(RoleEntity.class.getName(),mongoTemplate);
        e.setId(String.valueOf(nextId));
        mongoTemplate.save(e);
    }

    @Override
    public RoleEntity findById(String id) {
        return mongoTemplate.findById(id, RoleEntity.class);
    }

    @Override
    public void update(RoleEntity e) {
        Query query = new Query(Criteria.where("id").is(e.getId()));
        Update update = new Update();
        update.set("name", e.getName());
        update.set("description", e.getDescription());
        update.set("menus",e.getMenus());
        mongoTemplate.updateFirst(query, update, RoleEntity.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,RoleEntity.class);
    }

    @Override
    public List<RoleEntity> queryList(MenuParam e) {
        List<RoleEntity> list = mongoTemplate.findAll(RoleEntity.class);
        return list;
    }
}
