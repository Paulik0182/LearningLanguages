package com.example.learninglanguages.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.ThemeTask

class RootActivity : AppCompatActivity() {

    private lateinit var englishButton: Button
    private lateinit var kotlinButton: Button

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
}