package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity
import com.example.learninglanguages.domain.repos.FavoriteLessonsRepo
import com.squareup.picasso.Picasso

class LessonViewHolder(
    itemView: View,
    listener: (Long, FavouriteLessonEntity) -> Unit
) :
    RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)
    private val favoriteImageView = itemView.findViewById<ImageView>(R.id.favorite_image_view)
    private lateinit var lessonEntity: FavouriteLessonEntity
    private lateinit var likesStorage: FavoriteLessonsRepo
    private var courseId: Long = -1

    fun bind(courseId: Long, lessonEntity: FavouriteLessonEntity) {
        this.lessonEntity = lessonEntity
        this.courseId = courseId
        titleTextView.text = lessonEntity.name
        //проверка на наличие катинки
        if (lessonEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(lessonEntity.imageUrl)
                .placeholder(R.drawable.uploading_images_5)
                .into(coverImageView)
//        coverImageView.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
        }
        favoriteImageView.isVisible = lessonEntity.isFavorite
//        favoriteImageView.isVisible = likesStorage.isFavorite(courseId, lessonEntity.id)
//        likesStorage.isFavorite(courseId, lessonEntity.id)
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(courseId, lessonEntity)
        }

    }
}