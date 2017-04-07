package com.example.framework.app;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * function: BaseBAdapter 中viewHolder的封装
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/4/7.
 */

public class BaseViewHolder {

    //复用的View
    private View mConverView;
    //所有控件的集合
    private SparseArray<View> mViews;
    //记录位置信息
    private int mPosition;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConverView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConverView.setTag(this);
    }

    /**
     * 通过viewId 获取控件
     *
     * @param viewId 控件id
     * @param <T>    View 子类
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConverView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //返回ViewHolder
    public static BaseViewHolder getViewHolder(Context context, View convertView, ViewGroup parent,
                                               int layoutId, int position) {
        // BaseViewHolder 为空，创建新的，否则返回已存在的
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    //获取 convertView
    public View getConvertView() {
        return mConverView;
    }

    /**
     * 设置 TextView 的值
     *
     * @param ViewId
     * @param text
     * @return
     */
    public BaseViewHolder setText(int ViewId, String text) {
        TextView tv = getView(ViewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置 ImageView 的值
     *
     * @param viewId
     * @param resId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param visible
     * @return
     */
    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     *
     * @param viewId
     * @param checked
     * @return
     */
    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 设置触摸事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key,tag);
        return this;
    }

}
