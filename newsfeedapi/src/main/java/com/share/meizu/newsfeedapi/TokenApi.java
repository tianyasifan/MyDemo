package com.share.meizu.newsfeedapi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tongxiaotao on 18-3-1.
 */

public interface TokenApi {
    @GET(TouTiaoServerManager.TOKEN_URL)
    Call<TokenBean> getToken(@QueryMap Map<String, String> map);
}
