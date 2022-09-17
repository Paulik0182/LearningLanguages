package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseWithFavoriteLessonEntity(
    val id: Long = 0,
    val name: String = "",
    val logoUrl: String? = null,
    val lessons: MutableList<FavouriteLessonEntity> = mutableListOf(),
) : Parcelable