package com.example.learninglanguages.domain.kotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KotlinEntity(
    val id: Int,
    var cod: String,
    var context: String
) : Parcelable