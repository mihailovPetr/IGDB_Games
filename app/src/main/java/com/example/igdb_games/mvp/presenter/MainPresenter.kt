package com.example.igdb_games.mvp.presenter

import com.example.igdb_games.mvp.model.navigation.IScreens
import com.example.igdb_games.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.games())
    }

    fun backClicked() {
        router.exit()
    }
}
