package com.idealcn.expand;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.idealcn.expand.bean.ChildBean;
import com.idealcn.expand.bean.ParentBean;
import com.idealcn.expand.bean.WrapperBean;
import com.idealcn.expand.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        List<WrapperBean> wrapperBeanList = new ArrayList<>();
        ParentBean parentBean = null;
        List<WrapperBean> childWrapperBeanList = null;
        for (int x = 0; x < 10; x++) {
            parentBean = new ParentBean();
            childWrapperBeanList = new ArrayList<>();
            parentBean.setName("parent--"+x);
            parentBean.setGroupPosition(x);
            ChildBean childBean = null;
            for (int y = 0; y < Math.random() * 10; y++) {
                childBean = new ChildBean();
                childBean.setName("child--"+y);
                childWrapperBeanList.add(new WrapperBean(childBean));
            }
            parentBean.setChilWrapperBeanList(childWrapperBeanList);
            wrapperBeanList.add(new WrapperBean(parentBean));
        }



        MyAdapter myAdapter = new MyAdapter(this,wrapperBeanList);
        mainBinding.recyclerView.setAdapter(myAdapter);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        myAdapter.setOnScrollListener(new MyAdapter.OnListScrollListener() {
            @Override
            public void scrollTo(int pos) {
                mainBinding.recyclerView.scrollToPosition(pos);
            }
        });
    }
}
