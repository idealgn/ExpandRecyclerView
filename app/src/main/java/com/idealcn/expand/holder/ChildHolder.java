package com.idealcn.expand.holder;

import android.widget.CompoundButton;

import com.idealcn.expand.OnChildItemClickListener;
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

    public void bindView(final int position, final ChildBean childBean, final OnChildItemClickListener clickListener) {
        childBinding.tvChild.setText(childBean.getName());
        childBinding.cbChild.setChecked(childBean.isState());
        childBinding.cbChild.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                clickListener.onChildItemClick(position,childBean,check);
            }
        });
    }
}
