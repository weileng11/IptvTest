package com.iptv.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.iptv.test.R;

/**
 * classes:com.iptv.test.activity.AnimationActivity
 * @author lt
 * @date 2016/6/21
 * @time 10:41
 * @description  各种动画测试
 */
public class AnimationActivity extends FragmentActivity {
    
    private Button mDownUpBtn,mLeftRightBtn,mScaleBtn,mAlpaBtn,mRotateBtn;

    private ImageView imgSu;
    private ImageView iv1,iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity);
        
        initView();
    }
    
    public void initView(){
        imgSu=(ImageView)findViewById(R.id.img_bg);
        iv1=(ImageView)findViewById(R.id.img_v1);
        iv2=(ImageView)findViewById(R.id.img_v2);

        mDownUpBtn=(Button)findViewById(R.id.btn_down_toup);
        mLeftRightBtn=(Button)findViewById(R.id.btn_lefe_right);
        mAlpaBtn=(Button)findViewById(R.id.btn_alph);
        mScaleBtn=(Button)findViewById(R.id.btn_scale);
        mRotateBtn=(Button)findViewById(R.id.btn_rotate);

        mDownUpBtn.setOnClickListener(animationCilck);
        mLeftRightBtn.setOnClickListener(animationCilck);
        mAlpaBtn.setOnClickListener(animationCilck);
        mScaleBtn.setOnClickListener(animationCilck);
        mRotateBtn.setOnClickListener(animationCilck);
        
    }

    View.OnClickListener  animationCilck=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==mDownUpBtn){
                initAnim();
            }

            if(v==mLeftRightBtn){
                initleftToRgithAnim();
            }
            if(v==mAlpaBtn){
                setmAlpaAnim();
            }
            if(v==mScaleBtn){
                setScaleAnim();
            }

            if(v==mRotateBtn){
                setRotateAnim();
            }
            
        }
    };

    /**
     * 缩放
     */
    public void setRotateAnim(){
        Animation translateAnimation= AnimationUtils.loadAnimation(this, R.anim.rotate);//加载
        translateAnimation.setRepeatCount(1);
        imgSu.startAnimation(translateAnimation);
    }

    /**
     * 缩放
     */
    public void setScaleAnim(){
        Animation translateAnimation= AnimationUtils.loadAnimation(this, R.anim.sclae);//加载
        translateAnimation.setRepeatCount(1);
        imgSu.startAnimation(translateAnimation);
    }

    /**
     * 位移从左到右
     */
    public void initleftToRgithAnim(){
        Animation translateAnimation= AnimationUtils.loadAnimation(this, R.anim.in_from_left_toright);//加载
        translateAnimation.setRepeatCount(3);
        imgSu.startAnimation(translateAnimation);

        Animation translateAnimation1= AnimationUtils.loadAnimation(this, R.anim.in_from_left_toright);//加载
        translateAnimation1.setRepeatCount(3);
        iv1.startAnimation(translateAnimation1);

        Animation translateAnimation2= AnimationUtils.loadAnimation(this, R.anim.in_from_left_toright);//加载
        translateAnimation2.setRepeatCount(2);
        iv2.startAnimation(translateAnimation2);


    }

    /**
     * 透明动画
     */
    public void setmAlpaAnim(){
        Animation translateAnimation= AnimationUtils.loadAnimation(this, R.anim.alpha);//加载
        imgSu.startAnimation(translateAnimation);

        Animation translateAnimation1= AnimationUtils.loadAnimation(this, R.anim.alpha_show);//加载
        iv1.startAnimation(translateAnimation1);

        Animation translateAnimation2= AnimationUtils.loadAnimation(this, R.anim.alpha_show);//加载
        iv2.startAnimation(translateAnimation2);
    }
    
    
    /**
     * 位移从下到上
     */
    public void initAnim(){
        /** 设置位移动画 向右位移150 */
        Animation translateAnimation= AnimationUtils.loadAnimation(this, R.anim.in_from_down);//加载
        translateAnimation.setRepeatCount(3);
        imgSu.startAnimation(translateAnimation);

        Animation translateAnimation1= AnimationUtils.loadAnimation(this, R.anim.in_from_down_sd);//加载
        translateAnimation1.setRepeatCount(3);
        iv1.startAnimation(translateAnimation1);

        Animation translateAnimation2= AnimationUtils.loadAnimation(this, R.anim.in_from_down_sd);//加载
        translateAnimation2.setRepeatCount(3);
        iv2.startAnimation(translateAnimation2);


    }
}
