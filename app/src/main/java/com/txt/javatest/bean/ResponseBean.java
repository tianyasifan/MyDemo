package com.txt.javatest.bean;

/**
 * Created by huangyuansheng on 17-4-20.
 */

public class ResponseBean {
    private int code;
    private String message;
    private String redirect;
    private NewsResponseBean value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public NewsResponseBean getValue() {
        return value;
    }

    public void setValue(NewsResponseBean value) {
        this.value = value;
    }
}
