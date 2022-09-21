package com.kddang.library.recycleview

import android.content.Context
import android.view.View

class BaseRVAdapter<T>(context: Context, list: MutableList<T>, layoutIds: Int) :
    EasyRVAdapter<T>(context, list, layoutIds) {

    private var ic: ItemClick<T>? = null
    private var oic: OnItemClick<T>? = null
    private var oil: OnItemLongClick<T>? = null

    override fun onBindData(viewHolder: EasyRVHolder, position: Int, item: T) {
        viewHolder.apply {
            setOnItemViewClickListener { v ->
                ic?.apply {
                    onItemClick(v, v.id, position, item)
                }
                oic?.apply {
                    onItemClick(v, v.id, position, item)
                }
            }
        }
        viewHolder.apply {
            setOnItemViewLongClickListener { v ->
                ic?.apply {
                    onItemLongClick(v, v.id, position, item)
                }
                oil?.apply {
                    onItemLongClick(v, v.id, position, item)
                }
                false
            }
        }
    }

    /****
     * 设置 RecyclerView Item 点击事件 和 长按事件 的回调
     * @param ic
     */
    fun setClick(ic: ItemClick<T>) {
        this.ic = ic
    }

    /****
     * 设置 RecyclerView Item 点击事件 的回调
     * @param oic
     */
    fun setClick(oic: OnItemClick<T>) {
        this.oic = oic
    }

    /****
     * 设置 RecyclerView Item 长按事件 的回调
     * @param oil
     */
    fun setClick(oil: OnItemLongClick<T>) {
        this.oil = oil
    }

    /****
     * RecyclerView Item 的点击事件 和 长按事件
     * @param <T>
    </T> */
    interface ItemClick<T> {
        fun onItemClick(v: View, vId: Int, position: Int, item: T)
        fun onItemLongClick(v: View, vId: Int, position: Int, item: T)
    }

    /****
     * RecyclerView Item 的点击事件
     * @param <T>
    </T> */
    interface OnItemClick<T> {
        fun onItemClick(v: View, vId: Int, position: Int, item: T)
    }

    /****
     * RecyclerView Item 的长按事件
     * @param <T>
    </T> */
    interface OnItemLongClick<T> {
        fun onItemLongClick(v: View, vId: Int, position: Int, item: T)
    }
}