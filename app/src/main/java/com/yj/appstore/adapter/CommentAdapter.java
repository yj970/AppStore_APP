package com.yj.appstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yj.appstore.R;
import com.yj.appstore.model.bean.Comment;
import com.yj.appstore.v.recycle.YJRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends YJRecyclerAdapter {
    private List<Comment> list;

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public YJViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, null, false);
        return YJViewHolder.newInstance(view);
    }

    @Override
    public void onBindHolder(View itemView, int position) {
        ViewHolder holder = new ViewHolder(itemView);
        holder.tvName.setText(list.get(position).getUserName()+": ");
        holder.tvComment.setText(list.get(position).getComment());
        holder.tvTime.setText(list.get(position).getTime());
    }

    public void setData(List<Comment> data) {
        this.list = data;
    }

    public void addData(List<Comment> comments) {
        this.list.addAll(comments);
    }

    class ViewHolder extends YJViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
