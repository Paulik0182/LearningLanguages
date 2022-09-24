package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.LessonIdEntity
import com.example.learninglanguages.domain.interactor.FavoriteInteractor
import com.example.learninglanguages.domain.repos.FavoriteLessonsRepo

class FavoriteInteractionImpl(
    private val favoriteRepo: FavoriteLessonsRepo
) : FavoriteInteractor {

    // заводим коллекцию
    private val listeners: MutableList<Pair<LessonIdEntity, (Boolean) -> Unit>> = mutableListOf()

    override fun onLikeChange(lessonIdEntity: LessonIdEntity, callback: (Boolean) -> Unit) {
        // здесь делается механизм подписки (актуальные значения)
        val isFavorite = favoriteRepo.isFavorite(lessonIdEntity.courseId, lessonIdEntity.lessonId)
        callback(isFavorite)

        //кладем в listeners
        listeners.add(Pair(lessonIdEntity, callback))
    }

    override fun changeLike(lessonIdEntity: LessonIdEntity) {
        val isFavorite = favoriteRepo.isFavorite(lessonIdEntity.courseId, lessonIdEntity.lessonId)
        if (isFavorite) {
            favoriteRepo.removeEntity(lessonIdEntity)
        } else {
            favoriteRepo.addFavorite(lessonIdEntity)
        }
        //после того как листенер изменился необходимо об этом уведомить
        notifyListeners(lessonIdEntity, !isFavorite)
    }

    private fun notifyListeners(lessonIdEntity: LessonIdEntity, isFavorite: Boolean) {
        //проходимся по всему списку
        listeners.forEach {
            if (it.first == lessonIdEntity) {
                it.second.invoke(isFavorite)
            }
        }
    }
}