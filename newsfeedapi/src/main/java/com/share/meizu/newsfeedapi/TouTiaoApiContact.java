package com.share.meizu.newsfeedapi;

/**
 * Created by tongxiaotao on 18-3-7.
 */

public interface TouTiaoApiContact {
    interface TouTiaoFeedPresenter{
        void pullRefresh(String channel, long refreshTime);
        void loadMore(String channel, long timestamp);
    }

    interface TouTiaoFeedView{
        void onLoadMore();
        void onRefresh();
        void fail();
    }

    interface TouTiaoChannelPresenter{
        void loadChannels();
    }

    interface TouTiaoChannelView{
        void onLoadChannels();
    }
}
