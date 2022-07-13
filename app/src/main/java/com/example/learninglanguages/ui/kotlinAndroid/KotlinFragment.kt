package com.example.learninglanguages.ui.kotlinAndroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.kotlin.KotlinRepo

class KotlinFragment : Fragment() {
    private val add: App by lazy { requireActivity().application as App }

    private lateinit var kotlinRepo: KotlinRepo//экземпляр класса, для того чтобы получить данные

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kotlin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        kotlinRepo = add.kotlinRepo//достали из репо application (class App)

    }

    private fun initViews(view: View) {


    }
}