package com.kddang.library.abslistview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Checkable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.kddang.library.helper.ViewHelper


class EasyLVHolder(context: Context? = null, position: Int = 0, parent: ViewGroup? = null, var layoutId: Int = 0): ViewHelper.AbsListView<EasyLVHolder> {


    /**
     * findViewById后保存View集合
     */
    private val mViews = SparseArray<View>()
    private val mConvertViews = SparseArray<View>()

    private lateinit var mConvertView: View
    private var mLayoutId: Int = layoutId
    private var mPosition = position
    private var mContext: Context?  = context

    init {
        mConvertView = mConvertViews.get(layoutId)
        if (mConvertView == null) {
            mConvertView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false)
            mConvertViews.put(layoutId, mConvertView)
            mConvertView.tag = this
        }
    }

    operator fun <BVH : EasyLVHolder?> get(
        context: Context?,
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
        layoutId: Int
    ): BVH {
        return if (convertView == null) {
            EasyLVHolder(context!!, position, parent!!, layoutId) as BVH
        } else {
            val holder = convertView.tag as EasyLVHolder
            if (holder.mLayoutId != layoutId) {
                return EasyLVHolder(context!!, position, parent!!, layoutId) as BVH
            }
            holder.setPosition(position)
            holder as BVH
        }
    }

    fun getConvertView(): View{
        return mConvertViews.valueAt(0)
    }

    fun getConvertView(layoutId: Int) : View{
        return mConvertViews.valueAt(layoutId)
    }

    fun <V : View> getView(viewId: Int): V {
        var view = mViews[viewId]
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as V
    }

    fun setPosition(position: Int){
        this.mPosition = position
    }

    override fun setText(viewId: Int, value: String?): EasyLVHolder {
        val view = getView<TextView>(viewId)
        view.text = value
        return this
    }

    override fun setTextColor(viewId: Int, color: Int): EasyLVHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(color)
        return this
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setTextColorRes(viewId: Int, colorRes: Int): EasyLVHolder {
        val view = getView<TextView>(viewId)
        mContext?.let {
            view.setTextColor(it.resources.getColor(colorRes, null))
        }
        return this
    }

    override fun setImageResource(viewId: Int, imgResId: Int): EasyLVHolder {
        val view = getView<ImageView>(viewId)
        view.setImageResource(imgResId)
        return this
    }

    override fun setBackgroundColor(viewId: Int, color: Int): EasyLVHolder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    override fun setBackgroundColorRes(viewId: Int, colorRes: Int): EasyLVHolder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(colorRes)
        return this
    }

    override fun setImageDrawable(viewId: Int, drawable: Drawable?): EasyLVHolder {
        val view = getView<ImageView>(viewId)
        view.setImageDrawable(drawable)
        return this
    }

    override fun setImageDrawableRes(viewId: Int, drawableRes: Int): EasyLVHolder {
        val drawable = mContext?.let { it.resources.getDrawable(drawableRes, null) }
        return setImageDrawable(viewId, drawable)
    }

    override fun setImageUrl(viewId: Int, imgUrl: String?): EasyLVHolder {
        // TODO: Use Glide/Picasso/ImageLoader/Fresco
        return this
    }

    override fun setImageBitmap(viewId: Int, imgBitmap: Bitmap?): EasyLVHolder {
        val view = getView<ImageView>(viewId)
        view.setImageBitmap(imgBitmap)
        return this
    }

    override fun setVisible(viewId: Int, visible: Boolean): EasyLVHolder {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    override fun setVisible(viewId: Int, visible: Int): EasyLVHolder {
        val view = getView<View>(viewId)
        view.visibility = visible
        return this
    }

    override fun setTag(viewId: Int, tag: Any?): EasyLVHolder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this
    }

    override fun setTag(viewId: Int, key: Int, tag: Any?): EasyLVHolder {
        val view = getView<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    override fun setChecked(viewId: Int, checked: Boolean): EasyLVHolder {
        val view: Checkable = getView(viewId)
        view.isChecked = checked
        return this
    }

    override fun setAlpha(viewId: Int, value: Float): EasyLVHolder {
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

    override fun setTypeface(viewId: Int, typeface: Typeface?): EasyLVHolder {
        val view = getView<TextView>(viewId)
        view.setTypeface(typeface)
        view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        return this
    }

    override fun setTypeface(typeface: Typeface?, vararg viewIds: Int): EasyLVHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)
            view.setTypeface(typeface)
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    override fun setOnClickListener(viewId: Int, listener: View.OnClickListener?): EasyLVHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this
    }
}