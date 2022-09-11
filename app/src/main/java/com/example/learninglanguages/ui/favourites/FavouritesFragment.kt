package com.example.learninglanguages.ui.favourites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R

class FavouritesFragment : Fragment(R.layout.fragment_lesson) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View) {
        TODO("Not yet implemented")
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openFavourite(favouriteId: Long)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()//Агресивный способ проверке налмчия контроллера
    }

    companion object {
        @JvmStatic
        fun newInstance(favouriteId: Long) = FavouritesFragment().apply {
            arguments = Bundle().apply {
                putLong(Key.FAVOURITE_ID_ARGS_KEY, favouriteId)
            }
        }
    }
}