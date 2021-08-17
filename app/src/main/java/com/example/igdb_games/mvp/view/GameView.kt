package com.example.igdb_games.mvp.view

import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface GameView : MvpView {
    fun fillFields(game: Game, developers: List<Company>, publishers: List<Company>)
}
