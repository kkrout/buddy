package com.dong.buddy.system.controller;

import com.dong.buddy.common.Result;
import com.dong.buddy.exception.CommonException;
import com.dong.buddy.mongo.EntityFactory;
import com.dong.buddy.mongo.IMenuDao;
import com.dong.buddy.mongo.entity.MenuEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/system/menu")
public class MenuController {

    @Autowired
    private IMenuDao menuDao;

    @PostMapping(value = "/save")
    public Result save(@RequestBody MenuEntity e) throws Exception
    {

        if (StringUtils.isEmpty(e.getName())){
            throw new CommonException("菜单名称不能为空","10002");
        }

        if ( StringUtils.isEmpty(e.getId()) ) {
            MenuEntity baseEntity = EntityFactory.createBaseEntity(MenuEntity.class);
            baseEntity.setId(e.getId());
            baseEntity.setName(e.getName());
            baseEntity.setUrl(e.getUrl());
            baseEntity.setIconCls(e.getIconCls());
            baseEntity.setParentId(e.getParentId());
            menuDao.save(baseEntity);
        }else{
            menuDao.update(e);
        }

        return Result.SUCCESS();
    }

    @GetMapping(value = "/get/{id}")
    public MenuEntity get(@PathVariable String id) throws Exception
    {
        return menuDao.findById(id);
    }

    @GetMapping(value = "/list")
    public List<MenuEntity> list() throws Exception
    {
        return menuDao.queryList(null);
    }

    @PostMapping(value = "/delete/{id}")
    public Result delete(@PathVariable String id) throws Exception
    {
        menuDao.delete(id);
        return Result.SUCCESS();
    }

}
