package com.example.framework.app;

import android.app.Fragment;

/**
 * function: 基础Fragment
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/4/5.
 */

public abstract class BaseFragment1 extends Fragment{

    private Object pagetag;

    public void setPagetag (Object pagetag) {
        this.pagetag = pagetag;
    }

    public Object getPagetag () {
        return pagetag;
    }

    private String titleStr;
    private boolean isShowLeftBtn;
    private boolean isShowRightBtn;
    private boolean isShowDragBtn;

    public void initTitle (String titleStr, boolean isShowLeftBtn, boolean isShowRightBtn, boolean isShowDragBtn) {
        this.titleStr = titleStr;
        this.isShowLeftBtn = isShowLeftBtn;
        this.isShowRightBtn = isShowRightBtn;
        this.isShowDragBtn = isShowDragBtn;
    }

    public void resetTitle (String str) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null) {
            baseActivity.resetTitle(str);
        }
    }

    public String getTitle () {
        return titleStr;
    }

    //交给BaseTitleActivity 处理
    public boolean onBackFragment () {
        return false;
    }

    public boolean isShowLeftBtn () {
        return isShowLeftBtn;
    }

    public boolean isShowRightBtn () {
        return isShowRightBtn;
    }

    public boolean isShowDragBtn () {
        return isShowDragBtn;
    }

    public BackListener listener;

    public void setOnBackListener (BackListener listener) {
        this.listener = listener;
    }

    public interface BackListener {
        void setListener ();
    }



}
