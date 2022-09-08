package com.example.learninglanguages

object Key {

    //TEG open fragment
    internal const val TEG_TASK_CONTAINER_KEY = "TEG_TASK_CONTROLLER_LAYOUT_KEY"
    internal const val TEG_SUCCESS_CONTAINER_KEY = "TEG_SUCCESS_CONTAINER_KEY"
    internal const val SHOW_ALL_CONTAINER_KEY = "SHOW_ALL_CONTAINER_KEY"
    internal const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"
    internal const val FRAGMENT_LESSON_UUID_KEY = "FRAGMENT_LESSON_UUID_KEY"
    internal const val KEY_VIEW_MODEL_ID = "KEY_VIEW_MODEL_ID"

    //
    internal const val THEME_ID_ARGS_KEY = "THEME_ARGS_KEY"
    internal const val LESSON_ID_ARGS_KEY = "TASK_ARGS_KEY"
    internal const val COURSE_ID_ARGS_KEY = "COURSE_ID_ARGS_KEY"


    //Image
    internal const val VICTORY_FINISH_IMAGE_KEY =
        "https://avatars.mds.yandex.net/get-altay/2447509/2a0000017406f520b2f2412e0e106be83078/XXL"

    //res
    internal const val ASSETS_LESSONS_DIR_NAME_KEY =
        "lessons"//lessons потому что мы сканируем эту папку

    //ссылка на бд
    internal const val ASSETS_LESSONS_TASK_KEY =
        "lessons_task.json"

    //ссылка на бд
    internal const val DATABASE_URL_KEY =
        "https://lessons-147e1-default-rtdb.europe-west1.firebasedatabase.app/"

    //TEG для SingleLiveEvent
    internal const val TAG = "SingleLiveEvent"

    //Уведомления
    internal const val SHOW_NOTICE_TASK_FRAGMENT_KEY = "Вы ошиблись, попробуйте еще раз!!!"
}