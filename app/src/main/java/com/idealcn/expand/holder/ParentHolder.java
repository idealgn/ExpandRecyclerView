package com.idealcn.expand.holder;

import android.view.View;

import com.idealcn.expand.OnParentItemClickListener;
import com.idealcn.expand.bean.ParentBean;
import com.idealcn.expand.bean.WrapperBean;
import com.idealcn.expand.databinding.ItemParentBinding;

import java.util.List;

/**
 * Created by ideal on 17-8-2.
 */

public class ParentHolder extends BaseHolder {
    private ItemParentBinding parentBinding;
    public ParentHolder(ItemParentBinding parentBinding) {
        super(parentBinding.getRoot());
        this.parentBinding = parentBinding;
    }

    public void bindView(final int position, final ParentBean parentBean, final List<WrapperBean> childWrapperBeanList, final OnParentItemClickListener listener) {
        parentBinding.tvParent.setText(parentBean.getName());
        parentBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (childWrapperBeanList==null||childWrapperBeanList.size()==0)return;
                if (listener != null) {
                    if (parentBean.isExpand()) {
                        parentBean.setExpand(false);
                        listener.hide(parentBean, childWrapperBeanList);
                    } else {
                        parentBean.setExpand(true);
                        listener.expand(parentBean, childWrapperBeanList);
                    }
                }
            }
        });
    }
}
