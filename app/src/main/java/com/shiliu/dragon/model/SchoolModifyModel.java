package com.shiliu.dragon.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyangchao
 * @createTime
 * @description
 */
public class SchoolModifyModel implements Serializable {
    private static final String TAG = "SchoolModifyModel";

    private static final String EQUALS = " = ";

    private String name;

    List<Pair> modifyFilders = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFild(String name, Object value) {
        modifyFilders.add(new Pair(name,value));
    }

    @Override
    public String toString() {
        return "SchoolModifyModel{" +
                "name='" + name + '\'' +
                ", modifyFilders=" + modifyFilders +
                '}';
    }
}
