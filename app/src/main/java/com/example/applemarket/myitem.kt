package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class myItem(
    val imageicon: Int,
    val title: String,
    val address: String,
    val price: String,
    val like: String,
    val commet: String,
    val detail: String,
    val seller: String
)
    : Parcelable {
}
