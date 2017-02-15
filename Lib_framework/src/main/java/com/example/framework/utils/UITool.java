package com.example.framework.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * function:UI工具类
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7.
 */

public class UITool {
    /**
     * 展开view动画
     * @param v
     * @param duration
     */
    public static void expandView(final View v, long duration) {
        if (v.isShown())
            return;
        v.measure(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation expandAnimation = new Animation() {

            /**
             *
             * @see android.view.animation.Animation#applyTransformation(float,
             *      android.view.animation.Transformation)
             * @param interpolatedTime
             * @param t
             */

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                v.getLayoutParams().height = interpolatedTime == 1 ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            /**
             *
             * @see android.view.animation.Animation#willChangeBounds()
             * @return
             */

            @Override
            public boolean willChangeBounds() {

                return Boolean.TRUE;

            }
        };
        expandAnimation.setDuration(duration);
        expandAnimation.setInterpolator(new LinearInterpolator());
        v.startAnimation(expandAnimation);
    }
    /**
     * 收起view动画
     * @param v
     * @param duration
     */
    public static void collapseView(final View v, long duration) {
        if (!v.isShown())
            return;
        final int targetHeight = v.getMeasuredHeight();
        Animation collapseAnimation = new Animation() {

            /**
             *
             * @see android.view.animation.Animation#applyTransformation(float,
             *      android.view.animation.Transformation)
             * @param interpolatedTime
             * @param t
             */

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                // TODO Auto-generated method stub
                if (1 == interpolatedTime) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = (int) (targetHeight - (targetHeight * interpolatedTime));
                    v.requestLayout();
                }

            }

            /**
             *
             * @see android.view.animation.Animation#willChangeBounds()
             * @return
             */

            @Override
            public boolean willChangeBounds() {

                return true;

            }
        };
        collapseAnimation.setDuration(duration);
        collapseAnimation.setInterpolator(new LinearInterpolator());
        v.startAnimation(collapseAnimation);
    }
}
