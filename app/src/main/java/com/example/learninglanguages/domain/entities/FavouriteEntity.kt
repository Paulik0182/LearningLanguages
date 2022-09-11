package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavouriteEntity(
    val favouriteId: Long = 0,
    val lessonId: MutableList<LessonEntity> = mutableListOf()
) : Parcelable
