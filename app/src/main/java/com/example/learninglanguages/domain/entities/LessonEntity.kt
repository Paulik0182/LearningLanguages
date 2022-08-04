package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonEntity(
    val id: Long,
    val fileName: String,
    val name: String,
    val imageUrl: String
) : Parcelable