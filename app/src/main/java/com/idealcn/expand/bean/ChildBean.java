package com.idealcn.expand.bean;

/**
 * Created by ideal on 17-8-2.
 */

public class ChildBean {
    public static final int TYPE_CHILD = 0x10;
    private String name;
    private boolean state;
    private ParentBean parentBean;

    public ParentBean getParentBean() {
        return parentBean;
    }

    public void setParentBean(ParentBean parentBean) {
        this.parentBean = parentBean;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
