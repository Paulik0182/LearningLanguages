package com.example.learninglanguages.ui.task.answer

class TasksPresenter : TasksContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: TasksContract.View? = null

    override fun attach(view: TasksContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onTaskClick() {
        view?.openSuccessScreen()// реализовываем метод. view обязательно должна быть с ? (проверка на null)
    }
}