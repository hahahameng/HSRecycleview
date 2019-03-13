package com.jinquan.entity;

import java.util.ArrayList;
import java.util.List;

public class ColumnEntity {
    /**
     * 栏目
     */
    private String id; // ID
    private String title;// 标题
    private int level;// 层级
    List<ColumnEntity> subLists = new ArrayList<ColumnEntity>();// 子项数组

    private boolean isCheck;
    private boolean isParent;

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isParent() {
        return isParent;
    }


    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public ColumnEntity() {

    }


    public ColumnEntity(String _id, String _title, int _level, List<ColumnEntity> _list) {
        id = _id;
        title = _title;
        level = _level;
        subLists = _list;
        if(subLists!=null && subLists.size()>0){
           this.isParent = true;
        }else{
            this.isParent = false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<ColumnEntity> getSubLists() {
        return subLists;
    }

    public void setSubLists(List<ColumnEntity> subLists) {
        this.subLists = subLists;
    }

    @Override
    public String toString() {
        return "ColumnEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", level=" + level +
                ", subLists=" + subLists +
                ", isCheck=" + isCheck +
                ", isParent=" + isParent +
                '}';
    }
}
