package com.example.framework.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * function:UI工具类
 * Email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/2/7.
 */

public class UIToolUtil {
    /**
     * 展开view动画
     *
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
     *
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

    /**
     * 获取图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap readBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算图片采样率
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the requested height and width

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * @param context
     * @param resId
     * @param inSampleSize
     * @return
     */
    public static Bitmap readBitmap(Context context, int resId, int inSampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = inSampleSize;
        //获取图片资源
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 通过FileDescriptor加载图片
     *
     * @param fd
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap readBitmap(FileDescriptor fd, int reqWidth, int reqHeight) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;

        //Calculate the inSampleSize
        opt.inSampleSize = calculateInSampleSize(opt, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, opt);
    }

    public static Bitmap readBitmapFormLocalPath(Context context, String localPath) {
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;

            InputStream inputStream = new FileInputStream(new File(localPath));
            return BitmapFactory.decodeStream(inputStream, null, opt);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
