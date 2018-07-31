package com.share.meizu.newsfeedapi;

import java.util.List;

/**
 * Created by tongxiaotao on 18-3-2.
 */

public class FeedBean extends BaseBean{
    List<FeedDataBean> data;
    class FeedDataBean{
        String article_url;
        long behot_time;
        List<FeedDataCoverImgBean> cover_image_list;
        int cover_mode;
        long group_id;
        boolean has_gallery;
        boolean has_video;
        boolean has_gif;
        long publish_time;
        String share_url;
        String source;
        String tag;
        String title;
        FeedDataUserInfoBean user_info;
        int important_level;
        FeedDataVdInfoBean video_info;
        FeedDataStaInfoBean statistics_info;

        class FeedDataCoverImgBean{
            String url;
            int height;
            int width;
            List<FeedDataCoverImgUrlsBean> url_list;

            class FeedDataCoverImgUrlsBean{
                String url;
            }
        }

        class FeedDataUserInfoBean{
            String media_id;
            String name;
            String description;
            String avatar_url;
        }

        class FeedDataVdInfoBean{
            String video_id;
            int video_duration;
        }

        class FeedDataStaInfoBean{
            int comment_count;
            int bury_count;
            int digg_count;
        }
    }

}
