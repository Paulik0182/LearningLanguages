package com.example.learninglanguages.ui.favourites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavouriteEntity
import com.example.learninglanguages.domain.repos.FavoriteRepo

class FavouritesFragment : Fragment(R.layout.fragment_lesson) {

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var favoriteList: MutableList<FavouriteEntity>//кэшируем данные

    private lateinit var adapter: FavoritesAdapter

    private val listener = { favoriteEntity: FavouriteEntity ->
        fillView(favoriteEntity)
    }

    private lateinit var recyclerView: RecyclerView

    private val favoriteRepo: FavoriteRepo by lazy {
        app.lessonFavoriteRepo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        adapter.setData(favoriteRepo.getFavorite() as MutableList<FavouriteEntity>)

        adapter.setOnItemClickListener {
            TODO()
        }
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.lessons_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavoritesAdapter(mutableListOf(), listener)
        recyclerView.adapter = adapter
    }

    private fun fillView(favouriteEntity: FavouriteEntity) {
        adapter.setData(favoriteList)
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