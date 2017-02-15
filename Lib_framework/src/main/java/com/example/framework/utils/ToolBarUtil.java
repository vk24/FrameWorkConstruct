package com.example.framework.utils;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.R;

/**
 * function:公共标题栏
 * describe:更多描述
 * Email:yangchaozhi@outlook.com
 *
 * @author vinko on 2017/2/11.
 */

public class ToolBarUtil {

    private Toolbar toolbar;

    private FrameLayout fl_title_left;
    private ImageView iv_title_left;
    private TextView tv_title;
    private FrameLayout fl_title_right;
    private ImageView iv_title_right;

    public ToolBarUtil(Toolbar toolbar){

        this.toolbar = toolbar;
        this.fl_title_left = (FrameLayout) toolbar.findViewById(R.id.fl_title_left);
        this.iv_title_left = (ImageView) toolbar.findViewById(R.id.iv_title_left);
        this.tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        this.fl_title_right = (FrameLayout) toolbar.findViewById(R.id.fl_title_right);
        this.iv_title_right = (ImageView) toolbar.findViewById(R.id.iv_title_right);
    }

    // 设置标题位置
    public void setTitleGravity(int gravity){

        this.tv_title.setGravity(gravity);
    }

    // 设置左边按钮图标以及点击事件
    public void setLeftButton(int icon, View.OnClickListener listener){
        this.iv_title_left.setImageResource(icon);
        this.fl_title_left.setVisibility(View.VISIBLE);
        this.fl_title_left.setOnClickListener(listener);
    }

    // 设置右边按钮图标以及点击事件
    public void setRightButton(int icon, View.OnClickListener listener){
        this.iv_title_right.setImageResource(icon);
        this.fl_title_right.setVisibility(View.VISIBLE);
        this.fl_title_right.setOnClickListener(listener);
    }

    public void setToolbarTitle(String title){
        this.tv_title.setText(title);
    }
}
