package com.kddang.library.abslistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kddang.library.helper.DataHelper

abstract class EasyLVAdapter<T> : BaseAdapter, DataHelper<T> {
    private var mContext: Context
    private var mList: MutableList<T> = mutableListOf()
    private lateinit var layoutIds: IntArray
    private var mLInflater: LayoutInflater
    private lateinit var holder: EasyLVHolder

    constructor(context: Context, list: MutableList<T>, vararg layoutIds: Int) {
        mContext = context
        mList = list
        this.layoutIds = layoutIds
        mLInflater = LayoutInflater.from(mContext)
    }

    constructor(context: Context, list: MutableList<T>) {
        mContext = context
        mList = list
        mLInflater = LayoutInflater.from(mContext)
    }

    override fun getCount(): Int {
        return if (mList.size == 0) 0 else mList!!.size
    }

    override fun getItem(position: Int): Any {
        return (if (mList.size == 0) null else mList[position])!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val layoutId = getViewCheckLayoutId(position)
        holder = holder.get(mContext, position, convertView, parent, layoutId)
        convert(holder, position, mList!![position])
        return holder.getConvertView(layoutId)
    }

    private fun getViewCheckLayoutId(position: Int): Int {
        val layoutId = if (layoutIds == null || layoutIds!!.size == 0) {
            getLayoutId(position, mList!![position])
        } else {
            layoutIds!![getLayoutIndex(position, mList!![position])]
        }
        return layoutId
    }

    /**
     * 若构造函数没有指定layoutIds, 则必须重写该方法
     *
     * @param position
     * @param item
     * @return layoutId
     */
    fun getLayoutId(position: Int, item: T): Int {
        return 0
    }

    /**
     * 指定item布局样式在layoutIds的索引。默认为第一个
     *
     * @param position
     * @param item
     * @return
     */
    fun getLayoutIndex(position: Int, item: T): Int {
        return 0
    }

    abstract fun convert(holder: EasyLVHolder, position: Int, t: T)
    override fun addAll(list: List<T>): Boolean {
        val result = mList.addAll(list)
        notifyDataSetChanged()
        return result
    }

    override fun addAll(position: Int, list: List<T>): Boolean {
        val result: Boolean = mList.addAll(position, list)
        notifyDataSetChanged()
        return result
    }

    override fun add(data: T) {
        mList!!.add(data)
        notifyDataSetChanged()
    }

    override fun add(position: Int, data: T) {
        mList!!.add(position, data)
        notifyDataSetChanged()
    }

    override fun clear() {
        mList!!.clear()
        notifyDataSetChanged()
    }

    override operator fun contains(data: T): Boolean {
        return mList!!.contains(data)
    }

    override fun getData(index: Int): T {
        return mList!![index]
    }

    override fun modify(oldData: T, newData: T) {
        modify(mList!!.indexOf(oldData), newData)
    }

    override fun modify(index: Int, newData: T) {
        mList!![index] = newData
        notifyDataSetChanged()
    }

    override fun remove(data: T): Boolean {
        val result = mList!!.remove(data)
        notifyDataSetChanged()
        return result
    }

    override fun remove(index: Int) {
        mList!!.removeAt(index)
        notifyDataSetChanged()
    }
}