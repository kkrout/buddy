package com.dong.buddy.system.controller;

import com.dong.buddy.common.Result;
import com.dong.buddy.exception.CommonException;
import com.dong.buddy.mongo.EntityFactory;
import com.dong.buddy.mongo.IRoleDao;
import com.dong.buddy.mongo.entity.RoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/system/role")
public class RoleController {

    @Autowired
    private IRoleDao roleDao;

    @PostMapping(value = "/save",consumes = "application/json",produces = "application/json")
    public Result save(@RequestBody RoleEntity e) throws Exception
    {

        if (StringUtils.isEmpty(e.getName())){
            throw new CommonException("角色名称不能为空","10002");
        }

        if ( StringUtils.isEmpty(e.getId()) ) {
            RoleEntity baseEntity = EntityFactory.createBaseEntity(RoleEntity.class);
            baseEntity.setId(e.getId());
            baseEntity.setName(e.getName());
            baseEntity.setDescription(e.getDescription());
            baseEntity.setMenus(e.getMenus());
            roleDao.save(baseEntity);
        }else{
            roleDao.update(e);
        }

        return Result.SUCCESS();
    }

    @GetMapping(value = "/get/{id}")
    public RoleEntity get(@PathVariable String id) throws Exception
    {
        return roleDao.findById(id);
    }

    @GetMapping(value = "/list")
    public List<RoleEntity> list() throws Exception
    {
        return roleDao.queryList(null);
    }

    @PostMapping(value = "/delete/{id}")
    public Result delete(@PathVariable String id) throws Exception
    {
        roleDao.delete(id);
        return Result.SUCCESS();
    }

}
