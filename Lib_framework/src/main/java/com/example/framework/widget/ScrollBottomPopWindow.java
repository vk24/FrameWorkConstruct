package com.example.framework.widget;

import android.app.Activity;
import android.graphics.drawable.PaintDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.framework.R;

import java.util.ArrayList;

/**
 * function:底部中间选项大于4项可滚动的popwindow
 * @author vinko on 2017/2/8
 */
public class ScrollBottomPopWindow {

	private String TAG = "CcbScrollBottomPopWindow";

	private Activity mContext;// 上下文
	private View contentView;
	protected PopupWindow popupWindow;// 弹出框
    private popListAdapter popListAdapter;

    protected FrameLayout flBackground;//添加蒙版

	private LayoutInflater mInflater;
	private ArrayList<String> lists;// 数据源
	private ArrayList<Object> extras;// 数据源
	private ListView mListView;// 数据列表

	private int itemHeight;// 子项的高度
	private int maxHeight;// 最高高度
    private int lineHeight;//下划线高度

	private int selected = 1;//

	public ScrollBottomPopWindow(Activity mContext) {
		this.mContext = mContext;

        itemHeight = (int) mContext.getResources().getDimension(R.dimen.y144);
        maxHeight = (int) mContext.getResources().getDimension(R.dimen.y582);
        lineHeight= (int) mContext.getResources().getDimension(R.dimen.y1);
        initView();
	}

	private void initView() {
		mInflater = LayoutInflater.from(mContext);
		contentView = mInflater.inflate(R.layout.srcoll_popwindow_listview, null);

		mListView = (ListView) contentView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                Object obj = null;
                if (extras != null && extras.size() > 0) {
                    obj = extras.get(position);
                } else {
                    obj = "";
                }
                if (listener != null) {
                    listener.onSelected(lists.get(position), obj);
                }
                dismiss();
            }
        });
	}

    public void showPopWindow(View parentView){
        addMasking();
        // 透明状态栏
//        ((Activity) mContext).setb(mContext, mContext.getResources().getColor(R.color.transparent));
        int height=0;
        int itemTotal=mListView.getCount();
        if(itemTotal > 0 && itemTotal <= 4){
            height=itemTotal*itemHeight+itemTotal*lineHeight;
            mListView.setVerticalScrollBarEnabled(false);
        }else{
            height=maxHeight;
        }
        popupWindow=new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,height,true);//设置弹窗的宽和高
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);//展示的位置
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideMasking();
                    }
                },200);
            }
        });

    }
    
    public void setData(ArrayList<String> lists, ArrayList<Object> extras){
        this.lists=lists;
        this.extras=extras;
        if(lists.size() > 0 && mListView != null){
            popListAdapter =new popListAdapter();
            mListView.setAdapter(popListAdapter);
        }
    }

    class popListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder=null;
            if(convertView==null){
                holder=new Holder();
                convertView=mInflater.inflate(R.layout.srcoll_popwindow_list_item,null);
                holder.tvContent=(TextView)convertView.findViewById(R.id._content_);
                holder.arrow=(ImageView)convertView.findViewById(R.id._select_img_);
                holder.showLine=(ImageView)convertView.findViewById(R.id._showline_);
                convertView.setTag(holder);
            }else{
                holder=(Holder) convertView.getTag();
            }
            // 设置内容
            holder.tvContent.setText(lists.get(position));
            // 设置选中
            if (position == selected) {
                holder.arrow.setVisibility(View.VISIBLE);
            } else {
                holder.arrow.setVisibility(View.GONE);
            }

            // 下划线
            if (lists.size() == position) {
                holder.showLine.setVisibility(View.GONE);
            } else {
                holder.showLine.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }

    class Holder {
        private TextView tvContent;
        private ImageView arrow;
        private ImageView showLine;
    }

    public interface OnItemSelectListener{
        void onSelected(String str, Object obj);
    }

    private OnItemSelectListener listener;

    public void setOnItemSelectListener(OnItemSelectListener l){
        this.listener=l;
    }

    private void addMasking() {
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        flBackground = new FrameLayout(mContext);
        flBackground.setBackgroundColor(mContext.getResources().getColor(R.color.ccb_framework_red_dark));
        flBackground.setAlpha(0.5f);
        flBackground.setLayoutParams(frameParams);
        ((Activity) mContext).getWindow().addContentView(flBackground, frameParams);
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    protected void hideMasking() {
        // 移除蒙版
        ((ViewGroup) flBackground.getParent()).removeView(flBackground);
        // 恢复状态栏颜色
//        ((CcbActivity) mContext).setSystemBar(mContext, mContext.getResources().getColor(R.color.colorblue));
    }

}
