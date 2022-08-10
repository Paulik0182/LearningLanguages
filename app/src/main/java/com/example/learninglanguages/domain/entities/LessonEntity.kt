package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonEntity(
    val id: Long = 0,
    val name: String = "",
    @SerializedName("image_url")
    @PropertyName("image_url")
    @get: PropertyName("image_url")
    val imageUrl: String = "",
    @SerializedName("victory_image_url")
    @PropertyName("victory_image_url")
    @get: PropertyName("victory_image_url")
    val victoryImageUrl: String? = null,
    val tasks: List<TaskEntity> = emptyList()
) : Parcelable