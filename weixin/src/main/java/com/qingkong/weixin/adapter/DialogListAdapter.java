package com.qingkong.weixin.adapter;

import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qingkong.weixin.R;

import java.util.List;

/**
 * Created by yanghq on 2017/11/28.
 * 1653942463@qq.com
 */

public class DialogListAdapter extends BaseAdapter {
    private List<String> stringList ;

    public DialogListAdapter(List<String> stringList) {
        this.stringList = stringList;
    }
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View contentView = null;
        ViewHolder viewHolder = null;
        if (contentView == null) {
            viewHolder = new ViewHolder();
            contentView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_text, viewGroup,false);
            viewHolder.textView = contentView.findViewById(R.id.textView);
            contentView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) contentView.getTag();
            viewHolder.textView.setText(stringList.get(i));
        }

        return contentView;
    }
    static class ViewHolder{
        private TextView textView;
    }
}
