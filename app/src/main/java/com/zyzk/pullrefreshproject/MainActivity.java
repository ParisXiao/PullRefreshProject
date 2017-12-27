package com.zyzk.pullrefreshproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyzk.mysmartpullrefreshlibrary.MyRecycleView;
import com.zyzk.mysmartpullrefreshlibrary.MySmartPullRefresh;
import com.zyzk.pullrefreshproject.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ClassicsHeader smartHeader;
    private MyRecycleView myRecycleView;
    private ClassicsFooter smartFooter;
    private MySmartPullRefresh smartRefresh;

    private int page=1;
    private List<Bean> beanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRefresh();
        initData();
    }

    private void initData() {
        /**
         * 数据为空的空布局
         */
        View emptyView = View.inflate(this, R.layout.layout_empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView empty_img = (ImageView) emptyView.findViewById(R.id.empty_img);
        TextView empty_text = (TextView) emptyView.findViewById(R.id.empty_text);
        empty_img.setImageDrawable(getResources().getDrawable(R.drawable.shensujilu));
        empty_text.setText("你确定有数据？");
        myRecycleView.setEmptyView(emptyView);

        /**
         * smart的上拉加载下拉刷新
         */
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
        smartRefresh.setLoadmoreFinished(true);

    }
    private void getData(int page){
        int size=10*page;
        for (int i = 0; i < size; i++) {
            Bean bean=new Bean();
            bean.setId(i+"");
            bean.setName("第"+i+"个item");
            beanList.add(bean);
        }

    }

    private void initRefresh() {
        /**
         * 自定义上拉下拉提示
         */
        smartHeader.setPrimaryColor(Color.GRAY);//设置主题
        smartHeader.REFRESH_HEADER_PULLDOWN="你拉我试试(•́へ•́╬)";
        smartHeader.REFRESH_HEADER_REFRESHING="算你狠╭(╯^╰)╮";
        smartHeader.REFRESH_HEADER_FAILED="哈哈你个垃圾";
        smartHeader.REFRESH_HEADER_RELEASE="给我放手！！！";
        smartFooter.setPrimaryColor(Color.GRAY);
        smartFooter.REFRESH_FOOTER_ALLLOADED="别扯了，没有了！";
        smartFooter.REFRESH_FOOTER_PULLUP="这都被你发现，在拉试试！";


    }

    private void initView() {
        smartHeader = (ClassicsHeader) findViewById(R.id.smartHeader);
        myRecycleView = (MyRecycleView) findViewById(R.id.myRecycleView);
        smartFooter = (ClassicsFooter) findViewById(R.id.smartFooter);
        smartRefresh = (MySmartPullRefresh) findViewById(R.id.smartRefresh);
    }
}
