package com.hakanaksoy.mvvmkotlin.base

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.utility.extensions.hide
import com.hakanaksoy.mvvmkotlin.utility.extensions.show
import com.squareup.picasso.Picasso

@BindingAdapter("app:visibility")
fun setVisibilty(view: View, isVisible: Boolean) {
    view.hide()
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }
}

@BindingAdapter("app:setDrawableLink")
fun setDrawableLink(view: ImageView, link: String?) {
    if (link.isNullOrEmpty())
        return
    Picasso.get().cancelRequest(view)
    Picasso.get().load(link).into(view)
}


@BindingAdapter("app:setDrawable")
fun setDrawable(view: ImageView, drawable: Int) {
    view.setImageResource(drawable)
}

@BindingAdapter("app:explicitContentBorder")
fun explicitContentBorder(view: ImageView, explicitContent: Boolean) {
    if (explicitContent) {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.bg_explicit_content))
    } else
        view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.bg_explicit_content))
}

@BindingAdapter("app:explicitContentToolbar")
fun explicitContentToolbar(view: Toolbar, explicitContent: Boolean) {
    if (explicitContent) {
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.explicitContent))
    } else
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.colorCyan))
}


@BindingAdapter("app:setHtmlText")
fun setHtml(view: TextView, html: String?) {
    html?.let {
        view.text = Html.fromHtml(html)
    }
}


