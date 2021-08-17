package com.example.igdb_games.mvp.presenter

import com.example.igdb_games.di.IGameScopeContainer
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.navigation.IScreens
import com.example.igdb_games.mvp.model.repo.IGamesRepo
import com.example.igdb_games.mvp.presenter.list.IGameListPresenter
import com.example.igdb_games.mvp.view.GamesView
import com.example.igdb_games.mvp.view.list.GameItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class GamesPresenter() : MvpPresenter<GamesView>() {

    @Inject
    lateinit var gameScopeContainer: IGameScopeContainer
    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var gamesRepo: IGamesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    class GamesListPresenter : IGameListPresenter {
        val games = mutableListOf<Game>()
        override var itemClickListener: ((GameItemView) -> Unit)? = null

        override fun getCount() = games.size

        override fun bindView(view: GameItemView) {
            val game = games[view.pos]
            view.fillFields(game)
        }
    }

    val gamesListPresenter = GamesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        gamesListPresenter.itemClickListener = { itemView ->
            val game = gamesListPresenter.games[itemView.pos]
            router.navigateTo(screens.game(game))
        }
    }

    fun loadData() {
        gamesRepo.getGames()
            .observeOn(uiScheduler)
            .subscribe({ games ->
                gamesListPresenter.games.clear()
                gamesListPresenter.games.addAll(games)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        gameScopeContainer.releaseGameScope()
        super.onDestroy()
    }
}
