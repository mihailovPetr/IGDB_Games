package com.example.igdb_games.di.game

import com.example.igdb_games.di.game.module.GameModule
import com.example.igdb_games.mvp.presenter.CompanyPresenter
import com.example.igdb_games.mvp.presenter.GamesPresenter
import com.example.igdb_games.ui.adapter.GamesRVAdapter
import dagger.Subcomponent

@GameScope
@Subcomponent(
    modules = [
        GameModule::class
    ]
)
interface GameSubcomponent {
    fun inject(gamesPresenter: GamesPresenter)
    fun inject(gamesRVAdapter: GamesRVAdapter)
}