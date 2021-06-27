package com.shiliu.dragon.model;

import java.io.Serializable;

/**
 * @author ouyangchao
 * @createTime
 * @description
 */
public class Pair <N,V> implements Serializable {
    private N key;
    private V value;

    public Pair(N key, V value) {
        this.key = key;
        this.value = value;
    }

    public N getKey() {
        return key;
    }

    public void setKey(N key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
