package com.share.meizu.newsfeedapi;

/**
 * Created by tongxiaotao on 18-3-2.
 */

public class TokenBean extends BaseBean{
    TokenDataBean data;
    class TokenDataBean {
        long expires_in;
        String token;

        @Override
        public String toString() {
            return "[expires_in:" + expires_in + ", token:" + token + "]";
        }
    }

    @Override
    public String toString() {
        return "[ret:" + ret + ", msg:" + msg + ", req_id:" + req_id + ", data:" + data.toString() + "]";
    }
}
