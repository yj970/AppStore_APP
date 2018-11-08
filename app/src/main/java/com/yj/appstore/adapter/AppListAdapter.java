package com.yj.appstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yj.appstore.R;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.network.NetClient;
import com.yj.appstore.v.recycle.YJRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppListAdapter extends YJRecyclerAdapter{
    List<App> list;
    IClickListener clickListenerImpl;

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
    public void onBindHolder(View itemView, final int position) {
        ViewHolder holder = new ViewHolder(itemView);
        holder.tvName.setText(list.get(position).getAppName());
        Glide.with(itemView.getContext())
                .load(NetClient.baseUrl+list.get(position).getLogoPicUrl())
                .into(holder.ivLogo);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListenerImpl != null) {
                    clickListenerImpl.onClick(list.get(position).getPackageId());
                }
            }
        });
    }

    public void setData(List<App> data) {
        this.list = data;
    }

    public void addData(List<App> data) {
        this.list.addAll(data);
    }

    class ViewHolder extends YJViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IClickListener {
        void onClick(String packageId);
    }

    public void setClickListenerImpl(IClickListener clickListenerImpl) {
        this.clickListenerImpl = clickListenerImpl;
    }
}
