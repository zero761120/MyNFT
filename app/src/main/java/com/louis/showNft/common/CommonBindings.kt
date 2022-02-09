package com.louis.showNft.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import org.web3j.utils.Convert

object CommonBindings {

    @JvmStatic
    @BindingAdapter("visibleWithInvisible")
    fun setHideWithInVisible(view: View, isVisibility: Boolean) {
        view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean?) {
        view.visibility = if (show == true) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageResourceCircle")
    fun bindImageResourceCircle(view: ImageView, image: Int) {
        GlideApp.with(view.context)
            .load(image)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("imageFromUrl", "loadOriginalSize", requireAll = false)
    fun bindImageFromUrl(view: ImageView, imageUrl: String?, loadOriginalSize: Boolean) {
        Log.v("bindImageFromUrl", "imageUrl = $imageUrl, loadOriginalSize = $loadOriginalSize")
        if (imageUrl.isNullOrEmpty()) return
        GlideApp.with(view.context)
            .load(imageUrl)
            .apply {
                if (loadOriginalSize)
                    into(object : CustomViewTarget<ImageView, Drawable>(view) {
                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            Log.w("onLoadFailed", "load original size image failed")
                        }

                        override fun onResourceCleared(placeholder: Drawable?) {
                            // implement if needed
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            view.setImageDrawable(resource)
                        }
                    })
                else into(view)
            }
    }

    @JvmStatic
    @BindingAdapter("imageFromBitmap")
    fun bindImageFromBitmap(view: ImageView, bitmap: Bitmap?) {
        if (bitmap == null) {
            return
        }
        GlideApp.with(view.context)
            .load(bitmap)
            .into(view)
    }

    // Native binding will let int value to resource ID, so use this BindingAdapter prevent that happening.
    @JvmStatic
    @BindingAdapter("textString")
    fun setText(textView: TextView, any: Any?) {
        val value = any ?: ""
        textView.text = if (value is String) value else value.toString()
    }

    @JvmStatic
    @BindingAdapter("setEtherString")
    fun setEthText(textView: TextView, any: Any?) {
        val value = any ?: ""
        textView.text = if (value is String) {
            if (value.isNotEmpty()) {
                "Balance: ${Convert.fromWei(value, Convert.Unit.ETHER)} Ether"
            } else {
                ""
            }
        } else {
            value.toString()
        }
    }
}
