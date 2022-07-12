package com.example.learninglanguages.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.learninglanguages.R
import com.example.learninglanguages.ui.english.EnglishFragment
import com.example.learninglanguages.ui.kotlinAndroid.KotlinFragment

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        openEnglishFragment()
    }

    private fun openEnglishFragment(){
        val englishFragment: Fragment = EnglishFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.controller_layout, englishFragment, Key.TEG_ENGLISH_CONTROLLER_LAYOUT_KEY)
            .commit()
    }

    private fun openKotlinFragment(){
        val kotlinFragment: Fragment = KotlinFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.controller_layout, kotlinFragment, Key.TEG_KOTLIN_CONTROLLER_LAYOUT_KEY)
            .commit()
    }
}