package com.share.meizu.newsfeedapi;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tongxiaotao on 18-3-6.
 */

public interface TouTiaoServerApi {
    static final String BASE_URL = "https://open.isnssdk.com/";
    static final String TOKEN_PATH = "token/register/device/v1/";
    static final String FEED_PATH = "data/stream/topbuzz/v1/";
    static final String CHANNEL_PATH = "data/stream/topbuzz/channel/v1/";
    static final String PARTNER = "i18n_meizu_llq";
    static final String SECURE_KEY = "aa36975e590ec3d8656cbd1c12b483b3";

    @GET(TOKEN_PATH)
    Call<TokenBean> getToken(@QueryMap Map<String, String> map);

    @GET(FEED_PATH)
    Call<FeedBean> getFeed(@QueryMap Map<String,String> map);
}
