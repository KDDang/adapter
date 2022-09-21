package com.kddang.library.recycleview

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kddang.library.helper.DataHelper

abstract class EasyRVAdapter<T> : RecyclerView.Adapter<EasyRVHolder>, DataHelper<T> {

    private val mContext: Context
    private val mList: MutableList<T>
    private var layoutIds: IntArray
    private var mLInflater: LayoutInflater

    private val mConvertViews = SparseArray<View>()
    private var headerViewId = -1
    private var footerViewId = -2
    private var mHeaderView: View? = null
    private var mFooterView: View? = null

    companion object{
        const val TYPE_HEADER = -1
        const val TYPE_FOOTER = -2
    }

    constructor(context: Context, list: MutableList<T>, vararg layoutIds: Int) {
        mContext = context
        mList = list
        this.layoutIds = layoutIds
        mLInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EasyRVHolder {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return EasyRVHolder(mContext, headerViewId, mHeaderView!!)
        }
        if (mHeaderView != null && viewType == TYPE_FOOTER) {
            return EasyRVHolder(mContext, footerViewId, mFooterView!!)
        }
        if (viewType < 0 || viewType > layoutIds.size) {
            throw ArrayIndexOutOfBoundsException("layoutIndex")
        }
        require(layoutIds.isNotEmpty()) { "not layoutId" }
        val layoutId = layoutIds[viewType]
        var view = mConvertViews[layoutId]
        if (view == null) {
            view = mLInflater.inflate(layoutId, parent, false)
        }
        var viewHolder = if (view.tag != null) view.tag as EasyRVHolder else null
        if (viewHolder == null || viewHolder.getLayoutId() != layoutId) {
            viewHolder = EasyRVHolder(mContext, layoutId, view)
            return viewHolder
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: EasyRVHolder, position: Int) {
        if (getItemViewType(position) == TYPE_HEADER) return
        if (getItemViewType(position) == TYPE_FOOTER) return
        var mPosition = getPosition(position)
        val item = mList?.get(mPosition)
        onBindData(holder, mPosition, item!!)
    }

    override fun getItemCount(): Int {
        return if (mHeaderView == null && mFooterView == null) {
            mList?.size ?: 0
        } else if (mHeaderView != null && mFooterView != null) {
            if (mList == null) 2 else mList.size + 2
        } else {
            if (mList == null) 1 else mList.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && mHeaderView != null) {
            return TYPE_HEADER
        }
        if (position == itemCount - 1 && mFooterView != null) {
            return TYPE_FOOTER
        }
        var mPosition = getPosition(position)
        return getLayoutIndex(mPosition, mList!![mPosition])
    }

    /*****
     * 处理 GridLayoutManager
     * @param recyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager: GridLayoutManager = manager
            gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) gridManager.spanCount else 1
                }
            }
        }
    }

    /*****
     * 处理   StaggeredGridLayoutManager
     * @param holder
     */
    override fun onViewAttachedToWindow(holder: EasyRVHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
            val p = lp as StaggeredGridLayoutManager.LayoutParams
            p.isFullSpan = (holder.layoutPosition === 0)
        }
    }

    private fun getPosition(position: Int): Int {
        var mPosition = position
        if (mHeaderView != null || mFooterView != null) {
            mPosition -= mPosition
        }
        return mPosition
    }

    /**
     * 指定item布局样式在layoutIds的索引。默认为第一个
     *
     * @param position
     * @param item
     * @return
     */
    open fun getLayoutIndex(position: Int, item: T): Int {
        return 0
    }

    /****
     * 设置头部
     * @param headerViewId
     */
    open fun setHeaderView(headerViewId: Int): View? {
        mHeaderView = mLInflater.inflate(headerViewId, null)
        this.headerViewId = headerViewId
        notifyItemInserted(0)
        return mHeaderView
    }

    open fun setFooterView(footerViewId: Int): View? {
        mFooterView = mLInflater.inflate(footerViewId, null)
        this.footerViewId = footerViewId
        mList?.apply {
            notifyItemInserted(size)
        }
        return mFooterView
    }

    /****
     * 获取头部
     * @return
     */
    open fun getHeaderView(): View? {
        return mHeaderView
    }

    open fun getFooterView(): View? {
        return mFooterView
    }

    protected abstract fun onBindData(viewHolder: EasyRVHolder, position: Int, item: T)

    override fun addAll(list: List<T>): Boolean {
        val result = mList?.addAll(list)
        notifyDataSetChanged()
        return result!!
    }

    override fun addAll(position: Int, list: List<T>): Boolean {
        val result = mList?.addAll(position, list)
        notifyDataSetChanged()
        return result!!
    }

    override fun add(data: T) {
        mList?.add(data)
        notifyDataSetChanged()
    }

    override fun add(position: Int, data: T) {
        mList?.add(position, data)
        notifyDataSetChanged()
    }

    override fun clear() {
        mList?.apply {
            clear()
            notifyDataSetChanged()
        }
    }

    override operator fun contains(data: T): Boolean {
        return mList?.contains(data)!!
    }

    override fun getData(index: Int): T {
        return mList?.get(index)!!
    }

    override fun modify(oldData: T, newData: T) {
        mList?.let { modify(it.indexOf(oldData), newData) }
    }

    override fun modify(index: Int, newData: T) {
        mList?.apply {
            set(index, newData)
            notifyDataSetChanged()
        }
    }

    override fun remove(data: T): Boolean {
        val result = mList?.remove(data)
        notifyDataSetChanged()
        return result!!
    }

    override fun remove(index: Int) {
        mList?.removeAt(index)
        notifyDataSetChanged()
    }
}