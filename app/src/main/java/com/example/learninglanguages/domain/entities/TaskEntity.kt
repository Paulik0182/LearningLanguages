package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskEntity(
    val id: Long = 0,
    val task: String = "",
    @SerializedName("task_image_url")
    @PropertyName("task_image_url")
    @get: PropertyName("task_image_url")
    val taskImageUrl: String? = null,
    @SerializedName("variants_answer")
    @PropertyName("variants_answer")
    @get: PropertyName("variants_answer")
    val variantsAnswer: List<String> = emptyList(),
    @SerializedName("right_answer")
    @PropertyName("right_answer")
    @get: PropertyName("right_answer")
    val rightAnswer: String = "",
    val level: Int = 0
) : Parcelable