package com.dong.buddy.mongo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeEntity {

    private String id;

    private String text;

    private List<TreeEntity> nodes;

    public TreeEntity(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addNode(TreeEntity node) {
        if (nodes != null)
            nodes = new ArrayList<>();
        nodes.add(node);
    }

}
