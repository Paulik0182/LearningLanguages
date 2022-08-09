package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseLessonsRepoImpl : LessonRepo {

    private var data: List<LessonEntity> = emptyList()

    init {
        //ссылка на бд
        Firebase.database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
            }

            override fun onCancelled(error: DatabaseError) {
                val code = error.code
                // Failed to read value
            }
        })
    }

    //в данном случае мы не можем добавлять новые значения в репозиторий
    override fun addLesson(taskEntity: LessonEntity) {
        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
    }

    override fun getLessons(): List<LessonEntity> = data
}
