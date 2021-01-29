package m4trixtm.sublite.core.extension

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadFromUrl(url: String) =
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(16))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)