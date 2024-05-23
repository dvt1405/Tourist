package com.kt.apps.media.taipeitour.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.kt.apps.media.taipeitour.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:htmlText")
    fun setHtmlText(textView: TextView, text: String) {
        textView.text = HtmlCompat.fromHtml(
            text,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    @JvmStatic
    @BindingAdapter("android:imageUrl", "android:placeholderImg", requireAll = false)
    fun setImageUrl(textView: ImageView, imageUrl: String?, placeholder: Int) {
        setImageUrl(
            textView, imageUrl, ContextCompat.getDrawable(
                textView.context,
                placeholder
            )
        )
    }

    @JvmStatic
    @BindingAdapter("android:imageUrl", "android:placeholderImg", requireAll = false)
    fun setImageUrl(textView: ImageView, imageUrl: String?, placeholder: Drawable?) {
        GlideApp.with(textView)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = ContextCompat.getDrawable(
                        textView.context,
                        R.drawable.main_logo
                    )!!
                    target.onResourceReady(
                        drawable, DrawableCrossFadeTransition(300, true)
                    )
                    textView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(textView)
    }
}