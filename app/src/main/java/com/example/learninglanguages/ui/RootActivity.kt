package com.example.learninglanguages.ui

import android.os.Bundle
import android.widget.ImageView
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

    private lateinit var englishIv1: ImageView
    private lateinit var englishIv2: ImageView
    private lateinit var kotlinIv1: ImageView
    private lateinit var kotlinIv2: ImageView
    private lateinit var javaIv1: ImageView
    private lateinit var javaIv2: ImageView
    private lateinit var nameIv1: ImageView
    private lateinit var nameIv2: ImageView

    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        initViews()
        generateImageView()

        englishCv1.setOnClickListener {
            openTaskFragment(ThemeTask.ENGLISH)
            supportActionBar?.title = getText(R.string.english_level_1)
        }

        kotlinCv1.setOnClickListener {
            openTaskFragment(ThemeTask.KOTLIN)
            supportActionBar?.title = getText(R.string.kotlin_level_1)
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
            .add(R.id.container_card_view, fragment, Key.TEG_TASK_CONTAINER_KEY)
            .addToBackStack(null)
            .commit()
    }

    private fun initViews() {

        englishCv1 = findViewById(R.id.english1_card_view)
        englishCv2 = findViewById(R.id.english2_card_view)
        englishIv1 = findViewById(R.id.english1_image_view)
        englishIv2 = findViewById(R.id.english2_image_view)

        kotlinCv1 = findViewById(R.id.kotlin1_card_view)
        kotlinCv2 = findViewById(R.id.kotlin2_card_view)
        kotlinIv1 = findViewById(R.id.kotlin1_image_view)
        kotlinIv2 = findViewById(R.id.kotlin2_image_view)

        javaCv1 = findViewById(R.id.java1_card_view)
        javaCv2 = findViewById(R.id.java2_card_view)
        javaIv1 = findViewById(R.id.java1_image_view)
        javaIv2 = findViewById(R.id.java2_image_view)

        nameCv1 = findViewById(R.id.name1_card_view)
        nameCv2 = findViewById(R.id.name2_card_view)
        nameIv1 = findViewById(R.id.name1_image_view)
        nameIv2 = findViewById(R.id.name2_image_view)
    }

    //открываем фрагмент при завершении заданий
    override fun showSuccessScreen() {
        supportFragmentManager.popBackStack()//чистит стэк, после появления данного фрагмента нельзя будет вернутся

        val successFragment: Fragment = SuccessFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_card_view, successFragment, Key.TEG_SUCCESS_CONTAINER_KEY)
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

    private fun generateImageView() {

        englishIv1.setImageResource(R.drawable.english_book)
        englishIv2.setImageResource(R.drawable.ehglish_anime)

        kotlinIv1.setImageResource(R.drawable.kotlin_tel)
        kotlinIv2.setImageResource(R.drawable.kotlin)

        javaIv1.setImageResource(R.drawable.java_logotip_1)
        javaIv2.setImageResource(R.drawable.java_logotip_2)

        nameIv1.setImageResource(R.drawable.no_image)
        nameIv2.setImageResource(R.drawable.no_name)

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