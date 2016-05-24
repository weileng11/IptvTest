package com.iptv.test.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iptv.test.R;

/**
 * classes:com.iptv.test.fragment.DistanceFragment
 *
 * @author lt
 * @date 2016/5/23
 * @time 15:34
 * @description
 */
public class DistanceFragment  extends BaseFragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_distance, container, false);
        }
        return view;
    }
    @Override
    protected void loadInitLayout() {
        
    }

    @Override
    public void setIsLoad(boolean b) {

    }

    @Override
    public Object getRootView() {
        return view;
    }
}
