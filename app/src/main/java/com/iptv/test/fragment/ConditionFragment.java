package com.iptv.test.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iptv.test.R;
import com.iptv.test.activity.RecyclerViewTestActivity;
import com.iptv.tvwidget.view.ReflectItemView;

/**
 * classes:com.iptv.test.fragment.ConditionFragment
 *
 * @author lt
 * @date 2016/5/23
 * @time 15:34
 * @description
 */
public class ConditionFragment extends BaseFragment{

    private View view;
    private ReflectItemView iv2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_csong, container, false);
        }
        loadInitLayout();
        return view;
    }
    @Override
    protected void loadInitLayout() {
        iv2=(ReflectItemView)view.findViewById(R.id.iv_2);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RecyclerViewTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setIsLoad(boolean b) {

    }

    @Override
    public Object getRootView() {
        return view;
    }
}
