package com.example.framework.app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.framework.R;

/**
 * function: 作为BaseFragment的容器
 * describe: 初始化了头部标题栏
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/3/31.
 */

public class BaseTitleActivity extends BaseActivity {

    public BaseFragment1 getFragment() {
        return mFragment;
    }

    private BaseFragment1 mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.common_title_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClickListener() {

    }

    @Override
    protected void processClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long fragmentId = getIntent().getLongExtra(BaseActivityManager.FRAGMENT_TAG,0);
        BaseFragment1 baseFragment = BaseActivityManager.getInstance().getFragment(fragmentId);
        mFragment = baseFragment;

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        FID(R.id.common_btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (mFragment != null) {
            setPageTag(mFragment.getPagetag());
            useCustomTitle(mFragment.getTitle(),mFragment.isShowLeftBtn(),mFragment.isShowRightBtn());
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container,mFragment);
            transaction.commit();
            BaseActivityManager.getInstance().removeFragment(fragmentId);
        }

        setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragment.listener != null) {
                    mFragment.listener.setListener();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (null != mFragment && mFragment.onBackFragment()) {
            return;
        } else {
            super.onBackPressed();
        }
    }
}
