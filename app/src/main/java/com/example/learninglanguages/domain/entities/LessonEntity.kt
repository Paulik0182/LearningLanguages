package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonEntity(
    val id: Long,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("victory_image_url")
    val victoryImageUrl: String? = null,
    val tasks: List<TaskEntity>
) : Parcelable