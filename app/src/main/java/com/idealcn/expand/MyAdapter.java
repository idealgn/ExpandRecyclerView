package com.idealcn.expand;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idealcn.expand.bean.ChildBean;
import com.idealcn.expand.bean.ParentBean;
import com.idealcn.expand.bean.WrapperBean;
import com.idealcn.expand.databinding.ItemChildBinding;
import com.idealcn.expand.databinding.ItemParentBinding;
import com.idealcn.expand.holder.BaseHolder;
import com.idealcn.expand.holder.ChildHolder;
import com.idealcn.expand.holder.ParentHolder;

import java.util.List;

/**
 * Created by ideal on 17-8-2.
 */

public class MyAdapter extends RecyclerView.Adapter<BaseHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private  List<WrapperBean> wrapperBeanList;
    public MyAdapter(Context context, List<WrapperBean> wrapperBeanList){
        this.context = context;
        this.wrapperBeanList = wrapperBeanList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ParentBean.TYPE_PARENT: {
                ItemParentBinding parentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_parent,parent,false);
                return new ParentHolder(parentBinding);
            }
            case ChildBean.TYPE_CHILD: {
               ItemChildBinding childBinding =  DataBindingUtil.inflate(layoutInflater,R.layout.item_child,parent,false);
                return new ChildHolder(childBinding);
            }
            default:{
                ItemParentBinding parentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_parent,parent,false);
                return new ParentHolder(parentBinding);
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        WrapperBean wrapperBean = wrapperBeanList.get(position);
        switch (getItemViewType(position)){
            case ParentBean.TYPE_PARENT: {
                ParentHolder parentHolder = (ParentHolder) holder;
                parentHolder.bindView(position,wrapperBean.getParentBean(),listener);
                break;
            }
            case ChildBean.TYPE_CHILD: {
                ChildHolder childHolder = (ChildHolder) holder;
                childHolder.bindView(position,wrapperBean.getChildBean());
                break;
            }
            default:{
                ParentHolder parentHolder = (ParentHolder) holder;
                parentHolder.bindView(position, wrapperBean.getParentBean(), listener);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return wrapperBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        WrapperBean wrapperBean = wrapperBeanList.get(position);
        ParentBean parentBean = wrapperBean.getParentBean();
        return parentBean==null?ChildBean.TYPE_CHILD:ParentBean.TYPE_PARENT;
    }

    private OnParentItemClickListener listener = new OnParentItemClickListener() {
        @Override
        public void expand(ParentBean parentBean,List<WrapperBean> childWrapperBeanList) {
            int groupPosition = 0;
            int size = wrapperBeanList.size();
            for (int x = 0; x < size; x++) {
                ParentBean bean = wrapperBeanList.get(x).getParentBean();
                if (bean==null)continue;
                if (parentBean.getName().equals(bean.getName())) {
                    groupPosition = x;
                    break;
                }
            }
            //这里是重点
//           int groupPosition =  getRealPosition(parentBean);
            for (WrapperBean wrapperBean : childWrapperBeanList) {
                addItem(++groupPosition,wrapperBean);
            }

            if (groupPosition<= size -2&&mOnScrollListener!=null)
                mOnScrollListener.scrollTo(groupPosition);
        }

        @Override
        public void hide(ParentBean parentBean, List<WrapperBean> childWrapperBeanList) {
            int realPosition = 0;
            int size = wrapperBeanList.size();
            for (int x = 0; x < size; x++) {
                ParentBean bean = wrapperBeanList.get(x).getParentBean();
                if (bean==null)continue;
                if (parentBean.getName().equals(bean.getName())) {
                    realPosition = x;
                    break;
                }
            }
//            int realPosition = getRealPosition(parentBean);
            for (WrapperBean wrapperBean : childWrapperBeanList) {
                removeItem(realPosition+1);
            }
            if (mOnScrollListener!=null)
                mOnScrollListener.scrollTo(realPosition);
        }
    };

    private void removeItem(int realPosition) {
        wrapperBeanList.remove(realPosition);
        notifyItemRemoved(realPosition);
    }

    private int getRealPosition(ParentBean parentBean) {
        int size = wrapperBeanList.size();//拿到所有数据的大小
        int groupPosition = parentBean.getGroupPosition();//拿到初始位置
        int tempPosition = groupPosition;//保存这个初始位置
        for (int index = 0; index < size; index++) {
            ParentBean bean = wrapperBeanList.get(index).getParentBean();
            if (bean==null)continue;//bean为空，说明是child的item,不处理
            if (tempPosition<=bean.getGroupPosition())break;//当前parent后面的不做处理
            if (bean.isExpand()){//只处理展开的
                //因为不管是parent还是child，其本质都是RecyclerView的一个item,动态的增删数据集肯定会引起某个item的位置发生变化，这里是用来
                //获取item正确的位置
                groupPosition += bean.getChilWrapperBeanList().size();//
            }
        }
        return groupPosition;
    }

    private void addItem(int position, WrapperBean wrapperBean) {
        wrapperBeanList.add(position,wrapperBean);
        notifyItemInserted(position);
    }

    private OnListScrollListener mOnScrollListener;

    /**
     * 滚动监听接口
     */
    public interface OnListScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnListScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }
}
