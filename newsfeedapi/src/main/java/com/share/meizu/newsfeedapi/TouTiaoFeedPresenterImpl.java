package com.share.meizu.newsfeedapi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tongxiaotao on 18-3-7.
 */

public class TouTiaoFeedPresenterImpl implements TouTiaoApiContact.TouTiaoFeedPresenter {
    private TouTiaoApiContact.TouTiaoFeedView feedView;

    public TouTiaoFeedPresenterImpl(TouTiaoApiContact.TouTiaoFeedView feedView){
        this.feedView = feedView;
    }

    @Override
    public void pullRefresh(String channel, long time) {
        Call<FeedBean> call = TouTiaoServerManager.getInstance().getServerApi().getFeed(TouTiaoServerManager.getInstance().getFeedParam(channel, time, true));
        call.enqueue(new Callback<FeedBean>() {
            @Override
            public void onResponse(Call<FeedBean> call, Response<FeedBean> response) {
                if(feedView != null){
                    feedView.onRefresh();
                }
            }

            @Override
            public void onFailure(Call<FeedBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadMore(String channel, long timestamp) {
        Call<FeedBean> call = TouTiaoServerManager.getInstance().getServerApi().getFeed(TouTiaoServerManager.getInstance().getFeedParam(channel, time, true));
    }
}
