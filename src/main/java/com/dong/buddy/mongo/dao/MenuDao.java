package com.dong.buddy.mongo.dao;

import com.dong.buddy.mongo.EntityFactory;
import com.dong.buddy.mongo.IMenuDao;
import com.dong.buddy.mongo.entity.MenuEntity;
import com.dong.buddy.mongo.entity.SeqInfo;
import com.dong.buddy.mongo.entity.UserEntity;
import com.dong.buddy.util.UUIDUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

@Slf4j
@Component
public class MenuDao implements IMenuDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(MenuEntity e) {
        Long nextId = UUIDUtil.getNextId(MenuEntity.class.getName(),mongoTemplate);
        e.setId(String.valueOf(nextId));
        mongoTemplate.save(e);
    }

    @Override
    public MenuEntity findById(String id) {
        return mongoTemplate.findById(id, MenuEntity.class);
    }

    @Override
    public void update(MenuEntity e) {

        Query query = new Query(Criteria.where("id").is(e.getId()));
        Update update = new Update();
        update.set("name", e.getName());
        update.set("url", e.getUrl());
        update.set("iconCls", e.getIconCls());
        update.set("parentId", e.getParentId());
        update.set("parentName", e.getParentName());
        mongoTemplate.updateFirst(query, update, MenuEntity.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,MenuEntity.class);
    }

    @Override
    public List<MenuEntity> queryList(MenuParam e) {
        //mongoTemplate.dropCollection(MenuEntity.class);

        List<MenuEntity> menuList = mongoTemplate.findAll(MenuEntity.class);

        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }

        Map<String, MenuEntity> allMap = new HashMap<>();
        for (MenuEntity menu : menuList) {
            menu.setText(menu.getName());
            allMap.put(menu.getId(), menu);
        }

        List<MenuEntity> rootList = new ArrayList<>();
        for (MenuEntity menu : menuList) {
            if (menu.getParentId().equals("-1")) {
                rootList.add(menu);
            } else {
                MenuEntity menuEntity = allMap.get(menu.getParentId());
                if (menuEntity != null) {
                    menuEntity.addChild(menu);
                    menu.setParentName(menuEntity.getName());
                }
            }
        }

        return rootList;
    }

}
