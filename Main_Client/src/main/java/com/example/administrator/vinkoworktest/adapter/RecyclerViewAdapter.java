package com.example.administrator.vinkoworktest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.vinkoworktest.R;

import java.util.ArrayList;

/**
 * function:RecyclerView适配器
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> implements View.OnClickListener {

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<String> arrayList = new ArrayList<>();

    public RecyclerViewAdapter (ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        setArrayList(arrayList);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.recyclerview_item,null);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        String name = arrayList.get(position);
        holder.itemView.setTag(position);
        holder.img_icon.setBackgroundResource(R.mipmap.ic_launcher_round);
        holder.tv_name.setText(name);

    }

    @Override
    public int getItemCount() {
        return arrayList.size()>0?arrayList.size():0;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClicked(view,(int) view.getTag());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView img_icon;
        TextView tv_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private ItemClickListener listener;

    public interface ItemClickListener{
        void onItemClicked(View view ,int position);
    }

    public void setItemClickListener (ItemClickListener listener) {
        this.listener = listener;
    }
}
