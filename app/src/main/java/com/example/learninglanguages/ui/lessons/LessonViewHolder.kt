package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.squareup.picasso.Picasso

class LessonViewHolder(itemView: View, listener: (LessonEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private var progressBar: ProgressBar = itemView.findViewById(R.id.progress_cover_image_bar)

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)
    private lateinit var lessonEntity: LessonEntity

    fun bind(lessonEntity: LessonEntity) {
        this.lessonEntity = lessonEntity
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
            listener.invoke(lessonEntity)
        }
    }
}