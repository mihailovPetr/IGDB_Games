package com.example.igdb_games.mvp.model.entity

import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val imageId: String) : Parcelable {

    fun getURL(size: String) = "https://images.igdb.com/igdb/image/upload/t_$size/$imageId.jpg"

    companion object{
        const val THUMB = "thumb"
        const val ORIGINAL = "original"
        const val COVER_BIG = "cover_big"
        const val COVER_SMALL = "cover_small"
        const val LOGO_MED = "logo_med"
        const val SCREENSHOT_MED = "screenshot_med"
    }
}

fun ImageView.load(url: String){
    Glide.with(context).load(url).into(this)
}