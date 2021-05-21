package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company (
    val id : Int,
    val logoPath : String,
    val name: String,
    val originCountry: String
) : Parcelable