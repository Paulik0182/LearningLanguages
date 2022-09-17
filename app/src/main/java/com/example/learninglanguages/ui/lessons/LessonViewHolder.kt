package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity
import com.squareup.picasso.Picasso

class LessonViewHolder(itemView: View, listener: (Long, FavouriteLessonEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)
    private lateinit var lessonEntity: FavouriteLessonEntity
    private var courseId: Long = -1

    fun bind(courseId: Long, lessonEntity: FavouriteLessonEntity) {
        this.lessonEntity = lessonEntity
        this.courseId = courseId
        titleTextView.text = lessonEntity.name
//        coverImageView.load(lessonEntity.imageUrl)
        //проверка на наличие катинки
        if (lessonEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(lessonEntity.imageUrl)
                .placeholder(R.drawable.uploading_images_5)
                .into(coverImageView)
//        coverImageView.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
        }
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(courseId, lessonEntity)
        }
    }
}