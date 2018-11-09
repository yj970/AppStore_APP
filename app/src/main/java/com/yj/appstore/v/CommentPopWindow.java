package com.yj.appstore.v;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yj.appstore.R;
import com.yj.appstore.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentPopWindow {
    private PopupWindow mPopupWindow;
    private View mPopView;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.btn_comment)
    Button btnComment;
    ICommentPopWindowListener listener;
    private int height;

    public CommentPopWindow(Activity activity) {
        height = DensityUtil.dip2px(activity, 400);
         mPopView = activity.getLayoutInflater().inflate(R.layout.popwindow_comment_layout, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        mPopupWindow = new PopupWindow(mPopView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                height);
        // 点击popuwindow外让其消失
        mPopupWindow.setFocusable(true);//这里必须设置为true才能点击区域外或者消失
        mPopupWindow.setTouchable(true);//这个控制PopupWindow内部控件的点击事件
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        ButterKnife.bind(this, mPopView);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickComment(etComment.getText().toString());
                }
            }
        });
    }

    public void show() {
        mPopupWindow.showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);
    }

    public int getHeight() {
        return height;
    }

    public interface ICommentPopWindowListener{
        void onClickComment(String comment);
    }

    public void setListener(ICommentPopWindowListener listener) {
        this.listener = listener;
    }
}