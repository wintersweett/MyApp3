package com.Simba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 自定义的基类adapter
 * @param <T>
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {
    List<T> list;
    Context mContext;
    LayoutInflater mInflater;
    public BaseListAdapter(Context context,List<T> list){
        this.mContext=context;
        this.list=list;
        mInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        } else {
            return list.get(position) ;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position,convertView);
    }
    public void update(List<T> list) {
        this.list=list;
        notifyDataSetInvalidated();
        notifyDataSetChanged();

    }
    public abstract View getItemView(int position,View convertView);

}
