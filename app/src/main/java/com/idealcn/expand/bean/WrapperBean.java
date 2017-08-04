package com.idealcn.expand.bean;

import java.util.List;

/**
 * Created by ideal on 17-8-2.
 */

public class WrapperBean {
    private ParentBean parentBean;
    private ChildBean childBean;
    private List<WrapperBean> childWrapperBeanList;

    public WrapperBean(){}

    public WrapperBean(ParentBean parentBean) {
        this.parentBean = parentBean;
    }

    public WrapperBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public ParentBean getParentBean() {
        return parentBean;
    }

    public void setParentBean(ParentBean parentBean) {
        this.parentBean = parentBean;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public List<WrapperBean> getChildWrapperBeanList() {
        return childWrapperBeanList;
    }

    public void setChildWrapperBeanList(List<WrapperBean> childWrapperBeanList) {
        this.childWrapperBeanList = childWrapperBeanList;
    }
}
