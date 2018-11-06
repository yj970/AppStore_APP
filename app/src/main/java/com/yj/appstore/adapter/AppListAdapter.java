package com.yj.appstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yj.appstore.R;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.v.recycle.YJRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppListAdapter extends YJRecyclerAdapter{
    List<App> list;

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public YJViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, null, false);
        return YJViewHolder.newInstance(view);
    }

    @Override
    public void onBindHolder(View itemView, int position) {
        ViewHolder holder = new ViewHolder(itemView);
        holder.tvName.setText(list.get(position).getAppName());
    }

    public void setData(List<App> data) {
        this.list = data;
    }

    class ViewHolder extends YJViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
