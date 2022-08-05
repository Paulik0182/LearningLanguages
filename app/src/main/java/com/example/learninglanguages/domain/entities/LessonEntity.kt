package com.example.learninglanguages.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LessonEntity(
    val id: Long,
    @SerializedName("file_name")
    val fileName: String,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String
) : Parcelable