package com.Simba.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Simba.Utils.UtilsLog;
import com.Simba.entity.AdvanceduiEntity;
import com.example.think.myapp.R;

import java.util.List;

public class AdvancedUIAdapter extends BaseListAdapter {
    List<AdvanceduiEntity> list;
    ItemClick itemClickContext;


    public AdvancedUIAdapter(Context context, List list) {
        super(context, list);
        mContext=context;
        this.list=list;
        if (context instanceof ItemClick) {
            itemClickContext= (ItemClick) context;
        }
    }

    @Override
    public View getItemView(int position, View convertView) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_advancedui, null);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.tv_toui);
            convertView.setTag(holder);

        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        UtilsLog.log("zhm","listsize=="+list.size());
         final AdvanceduiEntity entity=list.get(position);
       holder.tv.setText(entity.to);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickContext.itemClick(entity);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }

 public interface ItemClick {
        void itemClick(AdvanceduiEntity entity);
    }
}
