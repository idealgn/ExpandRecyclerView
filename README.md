# ExpandRecyclerView
通过RecyclerView实现二级列表，只做了简单的数据显示,下一步给child增加多选功能
参考博客：http://blog.csdn.net/chengxu_hou/article/details/70344759，谢谢这位博主。


＃＃＃　2017-8-3
更改了item的position的查找方式，但是这里仍有个问题，如果数据量过大，那么这种查找是很耗时的。
在Adapter的 public void onBindViewHolder(BaseHolder holder, int position)方法中,当holder为childHolder时，这个position
是数据集中正确的位置，应该考虑怎么使用这个position避免查找。
