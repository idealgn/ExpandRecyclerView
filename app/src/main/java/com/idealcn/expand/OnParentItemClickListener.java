package com.idealcn.expand;

import com.idealcn.expand.bean.ParentBean;
import com.idealcn.expand.bean.WrapperBean;

import java.util.List;

/**
 * Created by ideal on 17-8-2.
 */

public interface OnParentItemClickListener {

    void expand(ParentBean parentBean, List<WrapperBean> childWrapperBeanList);

    void hide(ParentBean parentBean, List<WrapperBean> childWrapperBeanList);
}
