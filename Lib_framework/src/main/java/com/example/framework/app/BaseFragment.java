package com.example.framework.app;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类，所有的Fragment必须继承此类
 * Email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/2/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;
    public View convertView;
    private SparseArray<View> mViews;

    public abstract int getLayoutId();
    public abstract void initViews(View v);
    protected abstract void initData();
    protected abstract void initClickListener();
    protected abstract void processClick(View v);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            //设置已经不是可见的
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mViews = new SparseArray<>();
        convertView = inflater.inflate(getLayoutId(), container, false);
        initViews(convertView);
        isInitView = true;
        return convertView;
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }

    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            //如果不是第一次加载、不是可见的、不是初始化View，则不加载数据
            return;
        }
        //加载数据
        initClickListener();
        initData();
        //设置已经不是第一次加载
        isFirstLoad = false;
    }

    public <T extends View> T FindID(int viewId) {
        if (convertView != null) {
            T view = (T) mViews.get(viewId);
            if (view == null) {
                view = (T) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    public <T extends View> void setOnClick(T view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
