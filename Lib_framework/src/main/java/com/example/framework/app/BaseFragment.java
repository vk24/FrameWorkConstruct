package com.example.framework.app;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类，所有的Fragment必须继承此类
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/6.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(getLayoutId(),container,false);
        initViews(view);
        initData();
        return view;
    }

    /**
     * 获取布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化布局
     */
    public abstract void initViews(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
