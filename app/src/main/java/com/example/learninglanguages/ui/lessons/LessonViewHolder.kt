package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavoriteLessonEntity
import com.example.learninglanguages.domain.repos.FavoriteLessonsRepo
import com.squareup.picasso.Picasso
import org.koin.java.KoinJavaComponent.inject

class LessonViewHolder(
    itemView: View,
    listener: (Long, FavoriteLessonEntity) -> Unit
) :
    RecyclerView.ViewHolder(itemView) {

    private val favoriteRepo: FavoriteLessonsRepo by inject(FavoriteLessonsRepo::class.java)
    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)
    private var favoriteImageView = itemView.findViewById<ImageView>(R.id.favorite_image_view)
    private lateinit var lessonEntity: FavoriteLessonEntity
    private var courseId: Long = -1

    fun bind(courseId: Long, lessonEntity: FavoriteLessonEntity) {
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
        favoriteImageView.isVisible = favoriteRepo.isFavorite(courseId, lessonEntity.id)
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(courseId, lessonEntity)
        }

    }
}