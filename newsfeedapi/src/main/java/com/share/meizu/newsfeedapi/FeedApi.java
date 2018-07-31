package com.share.meizu.newsfeedapi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tongxiaotao on 18-3-2.
 */

public interface FeedApi {
    @GET(TouTiaoServerManager.FEED_URL)
    Call<FeedBean> getFeed(@QueryMap Map<String,String> map);
}
