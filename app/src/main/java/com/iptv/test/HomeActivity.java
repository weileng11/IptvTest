package com.iptv.test;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.iptv.test.adapter.FragAdapter;
import com.iptv.test.fragment.BaseFragment;
import com.iptv.test.fragment.ConditionFragment;
import com.iptv.test.fragment.ConditionFragment1;
import com.iptv.test.fragment.ConditionFragment2;
import com.iptv.test.fragment.DistanceFragment;
import com.iptv.test.fragment.TestFragment;
import com.iptv.test.fragment.WeilengFragment;
import com.iptv.test.ui.DepthPageTransformer;
import com.iptv.test.ui.FixedSpeedScroller;
import com.iptv.tvwidget.bridge.OpenEffectBridge;
import com.iptv.tvwidget.view.LinearMainLayout;
import com.iptv.tvwidget.view.MainUpView;
import com.iptv.tvwidget.view.ReflectItemView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * classes:com.iptv.test.HomeActivity
 *
 * @author lt
 * @date 2016/6/8
 * @time 14:26
 * @description
 */
public class HomeActivity extends FragmentActivity {
    private ConditionFragment mConditionFragment;
    private OpenEffectBridge mOpenEffectBridge;
    private ViewPager viewpager;
    private RadioGroup title_group;
    private RadioButton rb_xh;
    private RadioButton rb_gs;
    private RadioButton rb_science;
    private RadioButton rb_ap;
    private RadioButton rb_mf;
    private RadioButton lx_music;
    private View mOldFocus;
    private DistanceFragment mDistanceFragment;
    private ConditionFragment1 mConditionFragment1;
    private ConditionFragment2 mConditionFragment2;
    private TestFragment mTestFragment;
    private WeilengFragment mWeilengFragment;
    private MainUpView mainUpViewViewpager;
    //    private ShowPopupWindow showPopupWindow;
    private LinearMainLayout main_lay11;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private float scale;
//    private ArrayList<View> listView;

    private final String TAG = "MainActivity";
    private boolean isRunning = true;
    private FragAdapter adapter;
    private List<BaseFragment> fragments;
    public static String homeParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initApp();
    }

    private void initApp() {
        initView();//初始化
    }

    // 初始化
    protected void initView() {
        findViewById();
        setListener();
        initAllViewPager();
        setVPFouceChanged();
    }

    /**
     * 初始化viewpager
     */
    private void initAllViewPager() {
        fragments = new ArrayList<>();
        Bundle args = new Bundle();

        mConditionFragment = new ConditionFragment();
        fragments.add(mConditionFragment);
        mConditionFragment1 = new ConditionFragment1();
        fragments.add(mConditionFragment1);
        mConditionFragment2 = new ConditionFragment2();
        fragments.add(mConditionFragment2);

        mDistanceFragment = new DistanceFragment();
        fragments.add(mDistanceFragment);
        mTestFragment = new TestFragment();
        fragments.add(mTestFragment);

        mWeilengFragment = new WeilengFragment();
        fragments.add(mWeilengFragment);
        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        if (null != viewpager) {
            viewpager.setAdapter(adapter);
            viewpager.setPageTransformer(true, new DepthPageTransformer());
        }
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(
                    viewpager.getContext(), new AccelerateInterpolator());
            field.set(viewpager, scroller);
            scroller.setmDuration(700);
        } catch (Exception e) {
        }
    }

    private OpenEffectBridge mSavebridge;

    protected void findViewById() {
        title_group = (RadioGroup) findViewById(R.id.title_group);
        lx_music = (RadioButton) findViewById(R.id.rb_song);
        rb_xh = (RadioButton) findViewById(R.id.rb_story);
        rb_gs = (RadioButton) findViewById(R.id.rb_english);
        rb_mf = (RadioButton) findViewById(R.id.rb_guoxue);
        rb_science = (RadioButton) findViewById(R.id.rb_science);
        rb_ap = (RadioButton) findViewById(R.id.rb_ap);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        main_lay11 = (LinearMainLayout) findViewById(R.id.main_lay);
    }


    private void setVPFouceChanged() {
        viewpager.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {

            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                int pos = viewpager.getCurrentItem();
                View rootView = (View) fragments.get(pos).getRootView();
                mainUpViewViewpager = (MainUpView) rootView.findViewById(R.id.mainUpView_viewpager);
                final OpenEffectBridge bridge = (OpenEffectBridge) mainUpViewViewpager.getEffectBridge();
                if (!(newFocus instanceof ReflectItemView)) {
                    mainUpViewViewpager.setUnFocusView(mOldFocus);
                    bridge.setVisibleWidget(true);
                    mSavebridge = null;
                } else {
                    if (newFocus.getId() == R.id.iv_1) {
                        scale = 1f;
                    } else {
                        scale = 1.1f;
                        newFocus.bringToFront();
                    }

                    mSavebridge = bridge;
                    // 动画结束才设置边框显示，
                    // 是位了防止翻页从另一边跑出来的问题.
                    bridge.setOnAnimatorListener(new OpenEffectBridge.NewAnimatorListener() {
                        @Override
                        public void onAnimationStart(OpenEffectBridge bridge, View view, Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(OpenEffectBridge bridge1, View view, Animator animation) {
                            if (mSavebridge == bridge1)
                                bridge.setVisibleWidget(false);
                        }
                    });

                    mainUpViewViewpager.setFocusView(newFocus, mOldFocus, scale);
                }
                mOldFocus = newFocus;
            }
        });

    }


    protected void setListener() {
        int childCount = title_group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            title_group.getChildAt(i).setOnFocusChangeListener(ofcl);
        }
        title_group.setOnCheckedChangeListener(occl);
        viewpager.setOnPageChangeListener(opcl);
    }


    private ViewPager.OnPageChangeListener opcl = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switchFocusTab(position);
            for (int j = 0; j < fragments.size(); j++) {
                if (i == j) {
                    fragments.get(j).setIsLoad(true);
                } else {
                    fragments.get(j).setIsLoad(false);
                }
            }
            // 这里加入是为了防止移动过去后，移动的边框还在的问题.
            switch (position) {
                case 0:
                    viewpager.setCurrentItem(position);
                    break;
                case 1:
                    viewpager.setCurrentItem(position);
                    break;
                case 2:
                    viewpager.setCurrentItem(position);
                    break;
                case 3:
                    viewpager.setCurrentItem(position);
                    break;
                case 4:
                    viewpager.setCurrentItem(position);
                    break;
                case 5:
                    viewpager.setCurrentItem(position);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    int i = 0;
    private RadioGroup.OnCheckedChangeListener occl = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId() == title_group.getId())
                switch (checkedId) {
                    case R.id.rb_lx_music:
                        i = 0;
                        break;
                    case R.id.rb_xh:
                        i = 1;
                        break;
                    case R.id.rb_gs:
                        i = 2;
                        break;
                    case R.id.rb_mf:
                        i = 3;
                        break;
                    case R.id.rb_science:
                        i = 4;
                        break;
                    case R.id.rb_ap:
                        i = 5;
                        break;
                    default:
                        break;
                }
            Log.i(TAG, "onCheckedChanged: i" + i);
        }
    };

    private RadioGroup.OnFocusChangeListener ofcl = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(TAG, "onFocusChange: ");
                switch (v.getId()) {
                    case R.id.rb_lx_music:
                        lx_music.setChecked(true);
                        i = 0;
                        break;
                    case R.id.rb_xh:
                        rb_xh.setChecked(true);
                        i = 1;
                        break;
                    case R.id.rb_gs:
                        rb_gs.setChecked(true);
                        i = 2;
                        break;
                    case R.id.rb_mf:
                        rb_mf.setChecked(true);
                        i = 3;
                    case R.id.rb_science:
                        rb_science.setChecked(true);
                        i = 4;
                        break;
                    case R.id.rb_ap:
                        rb_ap.setChecked(true);
                        i = 5;
                        break;
                    default:
                        break;
                }
                viewpager.setCurrentItem(i);

            }
        }
    };

    /**
     * demo
     * 设置标题栏被选中，<br>
     * 但是没有焦点的状态.
     */
    public void switchFocusTab(int postion) {
        RadioButton childAt = (RadioButton) title_group.getChildAt(postion);
        int childCount = title_group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == postion) {
                childAt.setChecked(true);
            } else {
                childAt.setChecked(false);
            }
        }

    }
}
