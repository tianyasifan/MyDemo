package com.share.meizu.newsfeedapi;

import java.util.List;

/**
 * Created by tongxiaotao on 18-3-5.
 */

public interface IFeedView {
    void onLoadFeed(List<FeedBean.FeedDataBean> feeds);
}
