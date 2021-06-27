package com.shiliu.dragon.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyangchao
 * @createTime
 * @description
 */
public class UserModifyModel implements Serializable {
    private static final String EQUALS = " = ";

    private String id;

    List<Pair> modifyFilders = new ArrayList<Pair>();


    @Override
    public String toString() {
        return "UserModifyModel{" +
                "id='" + id + '\'' +
                ", modifyFilders=" + modifyFilders +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Pair> getModifyFilders() {
        return modifyFilders;
    }

    public void setModifyFilders(List<Pair> modifyFilders) {
        this.modifyFilders = modifyFilders;
    }

    public void addFild(String name, Object value) {
        modifyFilders.add(new Pair(name,value));
    }
}
