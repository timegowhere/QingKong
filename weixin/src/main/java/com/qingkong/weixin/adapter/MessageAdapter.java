package com.qingkong.weixin.adapter;

import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingkong.weixin.R;
import com.qingkong.weixin.bean.Message;
import com.qingkong.weixin.utils.ToastUtil;

import java.util.List;

/**
 * Created by yanghq on 2017/11/16.
 * 1653942463@qq.com
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> messageList ;
    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.tvName.setText(message.getUsername());
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void insertMessage(Message message){
        messageList.add(message);
        notifyItemInserted(messageList.size()-1);

       this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvMessage;
        private LinearLayout llytMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            llytMessage = itemView.findViewById(R.id.llytMessage);
            llytMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showSnackBar(view,tvMessage.getText().toString());
                }
            });
            llytMessage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return false;
                }
            });
        }

    }
}
