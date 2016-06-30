package com.txt.wxmvp;

/**
 * Created by txt on 2016/5/4.
 */
public class WXPresenterImp implements WXPresenter {
    private WXModel mWXModel;
    private WXView mWXView;

    public WXPresenterImp(WXView wxView){
        mWXView = wxView;
        mWXModel = new WXModelImp();
    }
    @Override
    public void loadDatas() {
        mWXView.setDatas(mWXModel.initDatas());
    }
}
