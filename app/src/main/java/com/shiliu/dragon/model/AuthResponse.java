package com.shiliu.dragon.model;


import java.io.Serializable;

/**
 * @author ouyangchao
 * @createTime
 * @description
 */

public class AuthResponse implements Serializable {
    public AuthResponse() {

    }

    AuthResponse(int code, Object message, String tokenId) {
        this.code = code;
        this.message = message;
        this.tokenId = tokenId;
    }

    private int code;
    private Object message;
    private String tokenId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
