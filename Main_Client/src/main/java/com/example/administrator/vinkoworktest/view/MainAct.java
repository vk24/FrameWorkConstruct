package com.example.administrator.vinkoworktest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vinkoworktest.R;
import com.example.framework.app.BaseActivity;
import com.example.framework.utils.StringUtil;
import com.example.framework.widget.ScrollBottomPopWindow;
import com.example.lib_network.SendRequest;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * function:首页
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/6
 */

public class MainAct extends BaseActivity {

    private Toolbar toolbar;
    private Button  btn2ShowPop,btnDrag;
    private TextView tv_show,tv_result;
    private SwitchCompat sc_btn;
    private AppCompatCheckBox checkBox;
    private ScrollBottomPopWindow scrollBottomPopWindow;
    private AppCompatEditText et_input;

    private ArrayList<String > keyList = new ArrayList<>();
    private ArrayList<Object > valueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useCustomTitle("首页",true,true);
        initView ();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnDrag = (Button) this.findViewById(R.id.btn_drag);
        btnDrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAct.this,DragRecyclerViewAct.class);
                startActivity(intent);
            }
        });

        btn2ShowPop = (Button) this.findViewById(R.id.button);
        tv_show = (TextView) this.findViewById(R.id.tv_show);
        tv_result = (TextView) this.findViewById(R.id.tv_result);


        for (int i = 0; i < 6; i++) {
            keyList.add(i + "");
            valueList.add("第" + i + "个");
        }

        scrollBottomPopWindow = new ScrollBottomPopWindow(this);
        scrollBottomPopWindow.setData(keyList,valueList);
        scrollBottomPopWindow.setOnItemSelectListener(new ScrollBottomPopWindow.OnItemSelectListener() {
            @Override
            public void onSelected(String str, Object obj) {
                tv_show.setText((String)obj);
            }
        });

        btn2ShowPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainAct.this,"执行交易",Toast.LENGTH_SHORT).show();
                        SendRequest.sendRequest("http://www.ccb.com/cn/html1/office/ebank/dzb/subject/15/0617yzylt/index.html", new SendRequest.requestListener() {
                            @Override
                            public void onResult(Response response) {
                                final Response response1 = response;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_result.setText(response1.body().toString() + response1.toString());
                                    }
                                });
                            }

                            @Override
                            public void onError(Exception e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_result.setText("e");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        sc_btn = (SwitchCompat) findViewById(R.id.sc_btn);
        sc_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainAct.this,"是",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainAct.this,"否",Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox = (AppCompatCheckBox) findViewById(R.id.des_cb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainAct.this,"是haha",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainAct.this,"否haha",Toast.LENGTH_SHORT).show();
                }
            }
        });

        et_input = (AppCompatEditText) findViewById(R.id.et_input);
        StringUtil.setEditTextInput(0,et_input);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setLeftBtnClickListener(View.OnClickListener listener) {
        super.setLeftBtnClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
