package com.qingkong.weixin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingkong.weixin.R;

///**
// * Created by yanghq on 2017/11/15.
// */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder>{
    public interface OnItemClickListener {
        void onClick(int position);
    }
    public OnItemClickListener onItemClickListener;
    public ChatListAdapter() {


    }
    public void setOnItemClickListen(OnItemClickListener onItemClickListener){
       this.onItemClickListener = onItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder. itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });

        holder.tvName.setText(position==0?"聊天室":"用户名"+position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivHeadshot;
        private TextView tvName;
        private TextView tvMessage;
        private TextView tvTime;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivHeadshot = itemView.findViewById(R.id.ivHeadshot);
            tvName =  itemView.findViewById(R.id.tvName);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);

        }
    }

}
