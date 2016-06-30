package com.txt.wxmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txt.mydemo.R;

import java.util.List;

/**
 * Created by txt on 2016/5/4.
 */
public class WXFragment extends Fragment implements WXView {

    private static String tag = WXFragment.class.getSimpleName();
    private WXPresenter mWXPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mWXPresenter = new WXPresenterImp(this);
        mWXPresenter.loadDatas();
        return inflater.inflate(R.layout.item_horizontal_list,container,false);
    }

    @Override
    public void setDatas(List<WXItemInfo> list) {
        Log.i(tag,"加载数据完成");
    }
}
