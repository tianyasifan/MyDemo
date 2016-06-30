package com.txt.wxmvp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2016/5/4.
 */
public class WXModelImp implements WXModel {
    private WXItemInfo wxItemInfo;
    private List<WXItemInfo> mList;

    @Override
    public List<WXItemInfo> initDatas() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            wxItemInfo = new WXItemInfo();
            wxItemInfo.setTitle("测试标题" + i);
            wxItemInfo.setContent("测试内容" + i);
            wxItemInfo.setTime(i + ":11");
            mList.add(wxItemInfo);
        }
        return mList;
    }
}
