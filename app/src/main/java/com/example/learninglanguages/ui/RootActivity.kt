package com.example.learninglanguages.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.ThemeTask

class RootActivity : AppCompatActivity(), TaskFragment.Controller, SuccessFragment.Controller {

    private lateinit var englishCv1: CardView
    private lateinit var englishCv2: CardView
    private lateinit var kotlinCv1: CardView
    private lateinit var kotlinCv2: CardView
    private lateinit var javaCv1: CardView
    private lateinit var javaCv2: CardView
    private lateinit var nameCv1: CardView
    private lateinit var nameCv2: CardView

    //    private lateinit var englishButton: Button
//    private lateinit var kotlinButton: Button
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        initViews()

        englishCv1.setOnClickListener {
            openTaskFragment(ThemeTask.ENGLISH)
        }

        kotlinCv1.setOnClickListener {
            openTaskFragment(ThemeTask.KOTLIN)
        }

//        englishButton.setOnClickListener {
//            openTaskFragment(ThemeTask.ENGLISH)
//        }
//
//        kotlinButton.setOnClickListener {
//            openTaskFragment(ThemeTask.KOTLIN)
//        }
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
//        englishButton = findViewById(R.id.english1_text_view)
//        kotlinButton = findViewById(R.id.kotlin1_text_view)

        englishCv1 = findViewById(R.id.english1_card_view)
        englishCv2 = findViewById(R.id.english2_card_view)

        kotlinCv1 = findViewById(R.id.kotlin1_card_view)
        kotlinCv2 = findViewById(R.id.kotlin2_card_view)

        javaCv1 = findViewById(R.id.java1_card_view)
        javaCv2 = findViewById(R.id.java2_card_view)

        nameCv1 = findViewById(R.id.name1_card_view)
        nameCv2 = findViewById(R.id.name2_card_view)
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