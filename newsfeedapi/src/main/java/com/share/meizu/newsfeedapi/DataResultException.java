package com.share.meizu.newsfeedapi;

import java.io.IOException;

/**
 * Created by tongxiaotao on 18-3-6.
 */

public class DataResultException extends IOException {
    int code;
    public DataResultException(int code){
        this.code = code;
    }
}
