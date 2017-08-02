package com.idealcn.expand.holder;

import com.idealcn.expand.bean.ChildBean;
import com.idealcn.expand.bean.WrapperBean;
import com.idealcn.expand.databinding.ItemChildBinding;

/**
 * Created by ideal on 17-8-2.
 */

public class ChildHolder extends BaseHolder {
    private ItemChildBinding childBinding;
    public ChildHolder(ItemChildBinding childBinding) {
        super(childBinding.getRoot());
        this.childBinding = childBinding;
    }

    public void bindView(int position, ChildBean childBean) {
        childBinding.tvChild.setText(childBean.getName());
    }
}
