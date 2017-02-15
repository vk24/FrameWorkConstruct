package com.example.framework.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.framework.R;
import com.example.framework.utils.SysStatusBarUtils;
import com.example.framework.utils.ToolBarUtil;

/**
 * function：使用的Material Design设计
 * Activity基类，所有的activity必须继承此类
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = BaseActivity.class.getSimpleName();

    public static final String ISTTRING = "i";

    protected Context mContext;

    /**
     * 初始化布局文件
     * @return
     */
    protected abstract int initLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 装载数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        Log.i(ISTTRING,this.getClass().getSimpleName() + "is create");
        ActivityManager.getInstance().addActivity(this);

        setContentView(initLayoutId());
        setToolBar();
        setImmerseLayout(findViewById(R.id.toolbars));
//        SysStatusBarUtils.transparencyBar(this);
        initView();
        initData();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = SysStatusBarUtils.getStatusBarHeight(this);
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ISTTRING,this.getClass().getSimpleName() + "is Destroy");
        ActivityManager.getInstance().removeActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String titleStr;
    private View.OnClickListener leftBtnListener;
    private View.OnClickListener rightBtnListener;

    public void setLeftBtnListener(View.OnClickListener listener){
        this.leftBtnListener = listener;
    }

    public void setRightBtnListener (View.OnClickListener listener){
        this.rightBtnListener = listener;
    }

    protected void setToolBar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbars);
        ToolBarUtil toolBarUtil = new ToolBarUtil(toolbar);
        if (titleStr != null && !"".equals(titleStr)) {
            toolBarUtil.setToolbarTitle(titleStr);
        } else {
            toolBarUtil.setToolbarTitle("默认标题");
        }
        if (rightBtnListener != null){
            toolBarUtil.setRightButton(R.mipmap.arrow,rightBtnListener);
        } else {
            toolBarUtil.setRightButton(R.mipmap.arrow, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"LEFT",Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (leftBtnListener != null){
            toolBarUtil.setLeftButton(R.mipmap.arrow,leftBtnListener);
        } else {
            toolBarUtil.setLeftButton(R.mipmap.arrow, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"RIGHT",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setTitle(String title){
        this.titleStr = title;
    }
}
