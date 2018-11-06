package com.yj.appstore.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yj.appstore.Contract;
import com.yj.appstore.R;
import com.yj.appstore.adapter.AppListAdapter;
import com.yj.appstore.model.bean.App;
import com.yj.appstore.presenter.AppListPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Contract.AppListView , SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    Contract.AppListPresenter appListPresenter;
    AppListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        srl.setOnRefreshListener(this);
        appListPresenter = new AppListPresenterImpl(this);
        adapter = new AppListAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        appListPresenter.refresh();
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onRefreshSuccess(List<App> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshFailure(String msg) {

    }
}
