package com.example.administrator.vinkoworktest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.vinkoworktest.R;
import com.example.framework.app.BaseFragment1;

/**
 * function: 类功能描述
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/3/31.
 */

public class TestFragment extends BaseFragment1 {

    private View rootView;
    private Context context;
    private TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_test,container,false);
        initView(rootView);
        return rootView;
    }

    public TestFragment () {
        initTitle("fragment", true, true,false);
    }

    private void initView(View rootView) {
        context = getActivity();
        tv = (TextView) rootView.findViewById(R.id.tv_);
        tv.setText("============");
    }
}
