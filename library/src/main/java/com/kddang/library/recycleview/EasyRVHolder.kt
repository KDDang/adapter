package com.kddang.library.recycleview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.SparseArray
import android.view.View
import android.view.View.OnLongClickListener
import android.view.animation.AlphaAnimation
import android.widget.Checkable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kddang.library.helper.ViewHelper

class EasyRVHolder(context: Context, @LayoutRes layoutId: Int, itemView: View): RecyclerView.ViewHolder(itemView),
    ViewHelper.RecyclerView<EasyRVHolder> {

    private val mViews: SparseArray<View> = SparseArray()
    private val mConvertView = itemView
    private val mLayoutId = layoutId
    private val mContext = context

    init {
        mConvertView.tag = this
    }

    fun setOnItemViewClickListener(listener: View.OnClickListener?): EasyRVHolder? {
        mConvertView.setOnClickListener(listener)
        return this
    }

    fun setOnItemViewLongClickListener(listener: OnLongClickListener?): EasyRVHolder? {
        mConvertView.setOnLongClickListener(listener)
        return this
    }

    fun <V : View?> getView(viewId: Int): V {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = mConvertView.findViewById<View>(viewId)
            mViews.put(viewId, view)
        }
        return view as V
    }

    fun getLayoutId(): Int {
        return mLayoutId
    }

    /**
     * 获取item布局
     *
     * @return
     */
    fun getItemView(): View? {
        return mConvertView
    }

    override fun setText(viewId: Int, value: String?): EasyRVHolder {
        val view: TextView = getView<TextView>(viewId)
        view.text = value
        return this
    }

    override fun setTextColor(viewId: Int, color: Int): EasyRVHolder {
        val view: TextView = getView<TextView>(viewId)
        view.setTextColor(color)
        return this
    }

    override fun setTextColorRes(viewId: Int, colorRes: Int): EasyRVHolder {
        val view: TextView = getView<TextView>(viewId)
        view.setTextColor(ContextCompat.getColor(mContext, colorRes))
        return this
    }

    override fun setImageResource(viewId: Int, imgResId: Int): EasyRVHolder {
        val view: ImageView = getView<ImageView>(viewId)
        view.setImageResource(imgResId)
        return this
    }

    override fun setBackgroundColor(viewId: Int, color: Int): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    override fun setBackgroundColorRes(viewId: Int, colorRes: Int): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.setBackgroundResource(colorRes)
        return this
    }

    override fun setImageDrawable(viewId: Int, drawable: Drawable?): EasyRVHolder {
        val view: ImageView = getView<ImageView>(viewId)
        view.setImageDrawable(drawable)
        return this
    }

    override fun setImageDrawableRes(viewId: Int, drawableRes: Int): EasyRVHolder {
        val drawable: Drawable = ContextCompat.getDrawable(mContext, drawableRes)!!
        return setImageDrawable(viewId, drawable)
    }

    override fun setImageUrl(viewId: Int, imgUrl: String?): EasyRVHolder {
        // TODO: Use Glide/Picasso/ImageLoader/Fresco
        return this
    }

    override fun setImageBitmap(viewId: Int, imgBitmap: Bitmap?): EasyRVHolder {
        val view: ImageView = getView<ImageView>(viewId)
        view.setImageBitmap(imgBitmap)
        return this
    }

    override fun setVisible(viewId: Int, visible: Boolean): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    override fun setVisible(viewId: Int, visible: Int): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.visibility = visible
        return this
    }

    override fun setTag(viewId: Int, tag: Any?): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.tag = tag
        return this
    }

    override fun setTag(viewId: Int, key: Int, tag: Any?): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    override fun setChecked(viewId: Int, checked: Boolean): EasyRVHolder {
        val view: Checkable = getView(viewId)
        view.isChecked = checked
        return this
    }

    override fun setAlpha(viewId: Int, value: Float): EasyRVHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView<View>(viewId).alpha = value
        } else {
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getView<View>(viewId).startAnimation(alpha)
        }
        return this
    }

    override fun setTypeface(viewId: Int, typeface: Typeface?): EasyRVHolder {
        val view: TextView = getView<TextView>(viewId)
        view.typeface = typeface
        view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        return this
    }

    override fun setTypeface(typeface: Typeface?, vararg viewIds: Int): EasyRVHolder {
        for (viewId in viewIds) {
            val view: TextView = getView<TextView>(viewId)
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    override fun setOnClickListener(viewId: Int, listener: View.OnClickListener?): EasyRVHolder {
        val view: View = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this
    }
}