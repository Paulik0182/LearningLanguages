package com.example.learninglanguages.domain.english

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EnglishEntity (
val id: Int,
var words: String,
var translation: String
) : Parcelable