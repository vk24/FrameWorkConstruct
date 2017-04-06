package com.example.framework.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.framework.R;
import com.example.framework.utils.SysStatusBarUtil;

/**
 * function：使用的Material Design设计
 * Activity基类，所有的activity必须继承此类
 * Email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/2/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = BaseActivity.class.getSimpleName();

    public static final String ISTTRING = "i";

    protected Context mContext;
    private SparseArray<View> mViews;


    //用于标记页面标识
    private Object pageTag;

    /**
     * 初始化布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 装载数据
     */
    protected abstract void initData();

    /*
    设置事件监听
     */
    protected abstract void initClickListener();

    protected abstract void processClick(View view) throws InstantiationException, IllegalAccessException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        Log.i(ISTTRING, this.getClass().getSimpleName() + "is onCreate");
        BaseActivityManager.getInstance().addActivity(this);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        SysStatusBarUtil.setImmerseLayout(this,findViewById(R.id.toolbars));
//        SysStatusBarUtil.transparencyBar(this);
        initView();
        initClickListener();
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void onClick(View view) {
        try {
            processClick(view);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过id找到view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T FID(int viewId) {
        T view = (T) mViews.get(viewId);
        if (view == null) {
            view = (T) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    public <T extends View> void setOnClick(T View) {
        View.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ISTTRING, this.getClass().getSimpleName() + "is onResume");
        BaseActivityManager.getInstance().setCurrentPre(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ISTTRING, this.getClass().getSimpleName() + "is onDestroy");
        BaseActivityManager.getInstance().removeActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private AppCompatImageButton commonRightBtn, commonLeftBtn;
    private AppCompatTextView commonTitle;

    private void findCommonTitleId() {
        commonRightBtn = (AppCompatImageButton) findViewById(R.id.common_btn_right);
        commonLeftBtn = (AppCompatImageButton) findViewById(R.id.common_btn_left);
        commonTitle = (AppCompatTextView) findViewById(R.id.common_tv_title);
    }

    /**
     * 设置标题样式
     *
     * @param title          标题文字
     * @param isDrawerMenu   左按钮展示位抽屉按钮？返回按钮
     * @param isShowRightBtn 是否展示右侧按钮
     */
    protected void useCustomTitle(String title, boolean isDrawerMenu, boolean isShowRightBtn) {
        findCommonTitleId();
        commonTitle.setText(title);

        if (isDrawerMenu) {
            setLeftBtnIsDrawer();
        } else {
            setLeftBtnIsBack();
        }

        if (isShowRightBtn) {
            commonRightBtn.setVisibility(View.VISIBLE);
            commonRightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    back2Home();
                }
            });
        } else {
            commonRightBtn.setVisibility(View.GONE);
        }
    }

    public void setLeftBtnIsDrawer() {
        commonLeftBtn.setBackgroundResource(R.mipmap.drawer);
        commonLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行抽屉式按钮的点击事件
            }
        });
    }

    public void setLeftBtnIsBack() {
        commonLeftBtn.setBackgroundResource(R.mipmap.back);
        commonLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handBack();
            }
        });
    }

    public void setLeftBtnClickListener(View.OnClickListener listener) {
        if (null != listener) {
            commonLeftBtn.setOnClickListener(listener);
        }
    }

    public void setRightBtnClickListener(View.OnClickListener listener) {
        if (null != listener) {
            commonRightBtn.setOnClickListener(listener);
        }
    }

    public void setLeftBtnBackground(int resId) {
        commonLeftBtn.setBackgroundResource(resId);
    }

    public void setRightBtnBackground(int resId) {
        commonRightBtn.setBackgroundResource(resId);
    }

    //重新设置标题文字
    protected void resetTitle(String title) {
        ((AppCompatTextView) findViewById(R.id.common_tv_title)).setText(title);
    }

    //执行回到主页的操作
    protected void back2Home() {

    }

    protected void handBack() {
        finish();
    }

    @Override
    public void finish() {
        BaseActivityManager.getInstance().removeActivity(this);
        super.finish();
    }

    public Object getPageTag() {
        return pageTag;
    }

    //设置页面标识
    public void setPageTag(Object pageTag) {
        this.pageTag = pageTag;
    }

}
