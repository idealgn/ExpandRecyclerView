package com.idealcn.expand.bean;

import java.util.List;

/**
 * Created by ideal on 17-8-2.
 */

public class ParentBean {

    public static final int TYPE_PARENT = 0x11;

    private String name;
    private int groupPosition;
    private boolean expand;
//    private List<WrapperBean> chilWrapperBeanList;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

//    public List<WrapperBean> getChilWrapperBeanList() {
//        return chilWrapperBeanList;
//    }
//
//    public void setChilWrapperBeanList(List<WrapperBean> chilWrapperBeanList) {
//        this.chilWrapperBeanList = chilWrapperBeanList;
//    }
}
