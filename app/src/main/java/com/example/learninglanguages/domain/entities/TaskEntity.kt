package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskEntity(
    val id: Long,
    val task: String,
    val taskImageUrl: String? = null,
    @SerializedName("variants_answer")
    val variantsAnswer: List<String>,
    @SerializedName("right_answer")
    val rightAnswer: String,
    val level: Int
) : Parcelable