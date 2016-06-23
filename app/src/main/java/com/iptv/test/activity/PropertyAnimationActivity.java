package com.iptv.test.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.iptv.test.R;

/**
 * classes:com.iptv.test.activity.PropertyAnimationActivity
 * @author lt
 * @date 2016/6/23
 * @time 9:58
 * @description
 */
public class PropertyAnimationActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reversal_activity);
    }


    /**
     * 翻转动画
     * @param view
     */
    public void rotateyAnimRun(final View view)
    {
         //翻转
//        ObjectAnimator//  
//                .ofFloat(view, "rotationX", 0.0F, 360.0F)//  
//                .setDuration(500)//  
//                .start();

        //淡入隐藏
        ObjectAnimator anim = ObjectAnimator//  
                .ofFloat(view, "alpha", 0.0F, 1.0F,0.0F)//  
                .setDuration(500);//  
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /**
     * alpha  scaleX  scaleY
     * @param view
     */
    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }

}
