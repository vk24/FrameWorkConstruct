package com.example.framework.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * function: baseAdapter封装
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/4/7.
 */

public abstract class BaseBAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected ArrayList<T> mDatas;
    protected int mLayoutId;

    public BaseBAdapter(Context context, ArrayList<T> datas, int layouId) {
        this.mContext = context;
        this.mDatas = (datas == null ? new ArrayList<T>() : datas);
        this.mLayoutId = layouId;
    }

    @Override
    public int getCount() {
        return (mDatas != null && mDatas.size() > 0) ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null && mDatas.size() > position) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDatas(ArrayList<T> datas, boolean flagReset) {
        if (datas == null) {
            this.mDatas = new ArrayList<>();
        } else {
            if (this.mDatas == null) {
                this.mDatas = new ArrayList<>();
            }
            if (flagReset) {
                this.mDatas = datas;
            } else {
                this.mDatas.addAll(datas);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId, position);
        T t = mDatas.get(position);
        //抽象出ViewHolder 让用户去实现填充数据
        bindData(holder, t);
        return holder.getConvertView();
    }

    public abstract void bindData(BaseViewHolder holder,T t);
}
