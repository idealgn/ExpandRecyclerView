package com.idealcn.expand;

import com.idealcn.expand.bean.ChildBean;

/**
 * Created by ideal on 17-8-4.
 */

public interface OnChildItemClickListener {
    void onChildItemClick(int position, ChildBean childBean, boolean check);
}
