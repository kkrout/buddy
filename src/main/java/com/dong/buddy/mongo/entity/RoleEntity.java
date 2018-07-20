package com.dong.buddy.mongo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection="RoleEntity")
public class RoleEntity extends BaseEntity {


    @Id
    private String id;

    private String name;

    private String description;

    private List<String> menus;

}
