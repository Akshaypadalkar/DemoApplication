package com.example.shaadicom

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"], requireAll = false)
fun setImage(view: ImageView, url: String?) {
    if (isValidString(url)) {
            Glide.with(BaseApplication.instance).load(url).
            error(R.drawable.user).placeholder(R.drawable.user).into(view)
        return
    } else
        view.setImageDrawable(BaseApplication.context.resources.getDrawable(R.drawable.user))
}
@BindingAdapter("app:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun isValidString(string: String?): Boolean {
    return (string != null && string.trim().isNotEmpty())
}