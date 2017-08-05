# ExpandRecyclerView
通过RecyclerView实现二级列表，只做了简单的数据显示,下一步给child增加多选功能
参考博客：http://blog.csdn.net/chengxu_hou/article/details/70344759，谢谢这位博主。
感谢:https://github.com/zhangke3016/MultilevelTreeList

### 思路

> 1. 数据
> 2.　Adapter如何处理显示数据
  


二级列表分为parent和其对应的child,那么对应的数据分别为ParentBean和ChildBean,数据类型是不一致的.故定义一个包装类WrapperBean来包装这两个bean.
然后将WrapperBean作为Adapter的数据类型填充.
```
public class WrapperBean{
  private ParentBean parentBean;
  private ChildBean childBean;
  private List<WrapperBean> childWrapperBeanList;
  public WrapperBean(ParentBean parentBean){
    this.parentBean = parentBean;
  }
  public WrapperBean(ChildBean childBean){
    this.childBean = childBean;
  }
  public void setChildWrapperBeanList(List<WrapperBean> childWrapperBeanList){
    this.childWrapperBeanList = childWrapperBeanList;
  }
}
```
  处理完数据，填充parent的数据是很简单的，处理展开和折叠是重点。(重要的地方总是可意会不可言传Ｏ（∩＿∩）Ｏ～)
  点击某个parent，取出其对应的child的数据集合和该parent在原始数据集中的确切位置(***这个位置很重要，不然它展开时对应的child的数据集会添加到不正确的位置***),获取parent的确切位置.
 ```
  int realPosition = 0;
            int size = wrapperBeanList.size();
            for (int x = 0; x < size; x++) {
                ParentBean bean = wrapperBeanList.get(x).getParentBean();
                if (bean==null)continue;
                //这个判断条件根据具体情况来确定
                if (parentBean.getName().equals(bean.getName())) {
                    realPosition = x;
                    break;
                }
            }
```
  

### 2017-8-3

更改了item的position的查找方式，但是这里仍有个问题，如果数据量过大，那么这种查找是很耗时的。
在Adapter的 public void onBindViewHolder(BaseHolder holder, int position)方法中,当holder为childHolder时，这个position
是数据集中正确的位置，应该考虑怎么使用这个position避免查找。
