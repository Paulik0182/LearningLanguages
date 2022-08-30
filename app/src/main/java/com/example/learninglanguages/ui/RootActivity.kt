package com.example.learninglanguages.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.ThemeTask

class RootActivity : AppCompatActivity(), TaskFragment.Controller, SuccessFragment.Controller {

    private lateinit var englishButton: Button
    private lateinit var kotlinButton: Button
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        initViews()

        englishButton.setOnClickListener {
            openTaskFragment(ThemeTask.ENGLISH)
        }

        kotlinButton.setOnClickListener {
            openTaskFragment(ThemeTask.KOTLIN)
        }
    }

    private fun openTaskFragment(themeTask: ThemeTask) {
        //читается когда (when) ThemeTask это ThemeTask.ENGLISH тогда запустить этот фрагмент
//        val fragment: Fragment = when (themeTask) {
//            ThemeTask.KOTLIN -> TaskFragment()
//            ThemeTask.ENGLISH -> TaskFragment()
//        }

        val fragment: Fragment = TaskFragment.newInstance(themeTask)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, fragment, Key.TEG_TASK_CONTAINER_KEY)
            .addToBackStack(null)
            .commit()
    }

    private fun initViews() {
        englishButton = findViewById(R.id.english_button)
        kotlinButton = findViewById(R.id.kotlin_button)
    }

    //открываем фрагмент при завершении заданий
    override fun showSuccessScreen() {
        supportFragmentManager.popBackStack()//чистит стэк, после появления данного фрагмента нельзя будет вернутся

        val successFragment: Fragment = SuccessFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, successFragment, Key.TEG_SUCCESS_CONTAINER_KEY)
            .commit()
    }

    override fun finishSuccessFragment() {
        supportFragmentManager.findFragmentByTag(Key.TEG_SUCCESS_CONTAINER_KEY)?.let {
            supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }
    }

    //это перехват нажатия на BackStack
//    override fun onBackPressed() {
//        if (backPressedTime + 3000 >= System.currentTimeMillis()) {
//            super.onBackPressed()
//            finish()
//        } else {
//            Toast.makeText(this, "Нажмите еще раз, чтобы выйти из приложения", Toast.LENGTH_LONG).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }
}