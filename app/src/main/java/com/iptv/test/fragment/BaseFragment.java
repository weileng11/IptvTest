package com.iptv.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * classes:com.iptv.test.fragment.BaseFragment
 *
 * @author lt
 * @date 2016/5/23
 * @time 15:36
 * @description
 */
public abstract class BaseFragment extends Fragment {
    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 加载布局文件
     */
    protected abstract void loadInitLayout();
   

    public Object getRootView() {
        return null;
    }

    public abstract void setIsLoad(boolean b);
}
