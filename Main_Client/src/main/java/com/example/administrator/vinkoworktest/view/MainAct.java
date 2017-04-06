package com.example.administrator.vinkoworktest.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.vinkoworktest.R;
import com.example.framework.app.BaseActivity;
import com.example.framework.app.BaseActivityManager;
import com.example.framework.widget.PayEditText;

/**
 * function:首页
 * Email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/2/6
 */

public class MainAct extends BaseActivity {

    private Button btn_db;
    private PayEditText et_pay;
    private EditText et_name, et_age, et_desc;
    private AppCompatImageView im_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("residueTime", "onCreate");
        useCustomTitle("首页", true, true);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        et_name = FID(R.id.et_name);
        et_age = FID(R.id.et_age);
        et_desc = FID(R.id.et_desc);
        btn_db = FID(R.id.btn_db);
        et_pay = FID(R.id.et_pay);
        et_pay.setInputCompleteCallback(new PayEditText.InputCompleteCallback() {
            @Override
            public void callback() {
                Toast.makeText(MainAct.this,"完成",Toast.LENGTH_LONG).show();
            }
        });
        im_ = FID(R.id.im_);
    }

    @Override
    protected void initClickListener() {
        setOnClick(btn_db);
    }

    @Override
    protected void processClick(View view){
        switch (view.getId()) {
            case R.id.btn_db :
                BaseActivityManager.getInstance().starActivity(MainAct.this,TestFragment.class);
                break;
        }
    }


    @Override
    protected void initData() {
        String  url = "https://img.gcall.com/dca5/M00/10/8E/wKhoNlggetaENWylAAAAAAAAAAA457.jpg";
        Glide.with(MainAct.this).load(url).skipMemoryCache(true).into(im_);
    }

}
