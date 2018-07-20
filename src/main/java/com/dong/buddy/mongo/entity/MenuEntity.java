package com.dong.buddy.mongo.entity;

import com.dong.buddy.advice.AutoIncKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection="MenuEntity")
public class MenuEntity extends BaseEntity {

    @AutoIncKey
    @Id
    private String id;

    private String name;

    private String url;

    private String iconCls;

    private String parentId;

    private String parentName;

    private String text;

    private List<MenuEntity> nodes;

    public void addChild(MenuEntity menu){
        if ( nodes == null) nodes = new ArrayList<>();
        nodes.add(menu);
    }


}
