package com.example.administrator.vinkoworktest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.administrator.vinkoworktest.R;
import com.example.administrator.vinkoworktest.adapter.RecyclerViewAdapter;
import com.example.administrator.vinkoworktest.api.ItemTouchCallbackHelper;
import com.example.framework.app.BaseActivity;

import java.util.ArrayList;

/**
 * function:RecyclerView测试页面
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/6
 */

public class DragRecyclerViewAct extends BaseActivity {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = DragRecyclerViewAct.this;
        useCustomTitle("第二页",false,false);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drag_recyclerview;
    }

    @Override
    protected void initView() {
        ArrayList<String> a = new ArrayList<>();
        a.add("中国");
        a.add("英国");
        a.add("美国");
        a.add("AAAA国");
        a.add("BBBB国");
        a.add("CCCC国");
        a.add("DDDD国");
        a.add("EEEE国");
        a.add("FFFF国");

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerViewAdapter(a);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchCallbackHelper(adapter));
        helper.attachToRecyclerView(recyclerView);


        recyclerView.setAdapter(adapter);
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
}
