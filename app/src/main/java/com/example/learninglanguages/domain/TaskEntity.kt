package com.example.learninglanguages.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskEntity(
    val id: Long,
    val task: String,
    val variantsAnswer: List<String>,
    val rightAnswer: String,
    val level: Int
) : Parcelable