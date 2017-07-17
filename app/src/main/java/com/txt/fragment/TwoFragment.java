package com.txt.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txt.mydemo.R;

/**
 * Created by txt on 2015/12/10.
 */
public class TwoFragment extends BaseFragment {

    public static TwoFragment newInstance(String extr){
        TwoFragment fragment = new TwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extr",extr);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view;
        view = inflater.inflate(R.layout.fragment_one,container,false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        Bundle bundle = getArguments();
        tv.setText(bundle.getString("extr"));
        return view;
    }
}
