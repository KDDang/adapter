package com.kddang.library.helper

import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.kddang.library.abslistview.EasyLVHolder
import com.kddang.library.recycleview.EasyRVHolder

interface ViewHelper {

    interface AbsListView<VH : EasyLVHolder>{
        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder viewHolder viewHolder
         */
        fun setText(viewId: Int, value: String?): VH

        /**
         * 设置textView文本颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder
         */
        fun setTextColor(viewId: Int, color: Int): VH

        /**
         * 设置textView文本颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        fun setTextColorRes(viewId: Int, colorRes: Int): VH

        /**
         * 设置imgView的图片,通过Id设置
         *
         * @param viewId   viewId
         * @param imgResId 图片Id
         * @return viewHolder viewHolder
         */
        fun setImageResource(viewId: Int, imgResId: Int): VH

        /**
         * 设置背景颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder viewHolder
         */
        fun setBackgroundColor(viewId: Int, color: Int): VH

        /**
         * 设置背景颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        fun setBackgroundColorRes(viewId: Int, colorRes: Int): VH

        /**
         * 设置img的Drawable
         *
         * @param viewId   viewId
         * @param drawable drawable
         * @return viewHolder
         */
        fun setImageDrawable(viewId: Int, drawable: Drawable?): VH

        /**
         * 设置img的Drawable
         *
         * @param viewId      viewId
         * @param drawableRes drawableId
         * @return viewHolder
         */
        fun setImageDrawableRes(viewId: Int, drawableRes: Int): VH

        /**
         * 设置img图片路径
         *
         * @param viewId viewId
         * @param imgUrl 图片路径
         * @return viewHolder
         */
        fun setImageUrl(viewId: Int, imgUrl: String?): VH

        /**
         * 设置img图片Bitmap
         *
         * @param viewId    viewId
         * @param imgBitmap imgBitmap
         * @return viewHolder
         */
        fun setImageBitmap(viewId: Int, imgBitmap: Bitmap?): VH

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible true(visible)/false(gone)
         * @return viewHolder
         */
        fun setVisible(viewId: Int, visible: Boolean): VH

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible visible,invisible,gone
         * @return viewHolder
         */
        fun setVisible(viewId: Int, visible: Int): VH

        /**
         * 设置控件的tag
         *
         * @param viewId viewId
         * @param tag    tag
         * @return viewHolder
         */
        fun setTag(viewId: Int, tag: Any?): VH

        /**
         * 设置控件tag
         *
         * @param viewId viewId
         * @param key    tag的key
         * @param tag    tag
         * @return viewHolder
         */
        fun setTag(viewId: Int, key: Int, tag: Any?): VH

        /**
         * 设置Checkable控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @return viewHolder
         */
        fun setChecked(viewId: Int, checked: Boolean): VH

        /**
         * 设置控件透明效果
         *
         * @param viewId viewId
         * @param value  透明值
         * @return viewHolder
         */
        fun setAlpha(viewId: Int, value: Float): VH

        /**
         * 设置TextView字体
         *
         * @param viewId   viewId
         * @param typeface typeface
         * @return viewHolder
         */
        fun setTypeface(viewId: Int, typeface: Typeface?): VH

        /**
         * 设置多个TextView字体
         *
         * @param typeface typeface
         * @param viewIds  viewId组合
         * @return viewHolder
         */
        fun setTypeface(typeface: Typeface?, vararg viewIds: Int): VH

        /**
         * 设置监听
         *
         * @param viewId
         * @param listener
         * @return
         */
        fun setOnClickListener(viewId: Int, listener: View.OnClickListener?): VH
    }

    interface RecyclerView<VH : EasyRVHolder> {
        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder viewHolder viewHolder
         */
        fun setText(viewId: Int, value: String?): VH

        /**
         * 设置textView文本颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder
         */
        fun setTextColor(viewId: Int, color: Int): VH

        /**
         * 设置textView文本颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        fun setTextColorRes(viewId: Int, colorRes: Int): VH

        /**
         * 设置imgView的图片,通过Id设置
         *
         * @param viewId   viewId
         * @param imgResId 图片Id
         * @return viewHolder viewHolder
         */
        fun setImageResource(viewId: Int, imgResId: Int): VH

        /**
         * 设置背景颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder viewHolder
         */
        fun setBackgroundColor(viewId: Int, color: Int): VH

        /**
         * 设置背景颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        fun setBackgroundColorRes(viewId: Int, colorRes: Int): VH

        /**
         * 设置img的Drawable
         *
         * @param viewId   viewId
         * @param drawable drawable
         * @return viewHolder
         */
        fun setImageDrawable(viewId: Int, drawable: Drawable?): VH

        /**
         * 设置img的Drawable
         *
         * @param viewId      viewId
         * @param drawableRes drawableId
         * @return viewHolder
         */
        fun setImageDrawableRes(viewId: Int, drawableRes: Int): VH

        /**
         * 设置img图片路径
         *
         * @param viewId viewId
         * @param imgUrl 图片路径
         * @return viewHolder
         */
        fun setImageUrl(viewId: Int, imgUrl: String?): VH

        /**
         * 设置img图片Bitmap
         *
         * @param viewId    viewId
         * @param imgBitmap imgBitmap
         * @return viewHolder
         */
        fun setImageBitmap(viewId: Int, imgBitmap: Bitmap?): VH

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible true(visible)/false(gone)
         * @return viewHolder
         */
        fun setVisible(viewId: Int, visible: Boolean): VH

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible visible,invisible,gone
         * @return viewHolder
         */
        fun setVisible(viewId: Int, visible: Int): VH

        /**
         * 设置控件的tag
         *
         * @param viewId viewId
         * @param tag    tag
         * @return viewHolder
         */
        fun setTag(viewId: Int, tag: Any?): VH

        /**
         * 设置控件tag
         *
         * @param viewId viewId
         * @param key    tag的key
         * @param tag    tag
         * @return viewHolder
         */
        fun setTag(viewId: Int, key: Int, tag: Any?): VH

        /**
         * 设置Checkable控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @return viewHolder
         */
        fun setChecked(viewId: Int, checked: Boolean): VH

        /**
         * 设置控件透明效果
         *
         * @param viewId viewId
         * @param value  透明值
         * @return viewHolder
         */
        fun setAlpha(viewId: Int, value: Float): VH

        /**
         * 设置TextView字体
         *
         * @param viewId   viewId
         * @param typeface typeface
         * @return viewHolder
         */
        fun setTypeface(viewId: Int, typeface: Typeface?): VH

        /**
         * 设置多个TextView字体
         *
         * @param typeface typeface
         * @param viewIds  viewId组合
         * @return viewHolder
         */
        fun setTypeface(typeface: Typeface?, vararg viewIds: Int): VH

        /**
         * 设置监听
         *
         * @param viewId
         * @param listener
         * @return
         */
        fun setOnClickListener(viewId: Int, listener: View.OnClickListener?): VH
    }
}