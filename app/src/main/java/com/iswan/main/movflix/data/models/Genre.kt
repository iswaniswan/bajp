package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre (
    val id : Int?,
    val name : String?
) : Parcelable

fun ArrayList<Genre>.concatName() =
    this.joinToString(", ") {
        it.name.toString() }