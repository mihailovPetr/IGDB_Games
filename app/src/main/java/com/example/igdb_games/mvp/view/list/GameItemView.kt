package com.example.igdb_games.mvp.view.list

import com.example.igdb_games.mvp.model.entity.Game

interface GameItemView : IItemView {
    fun fillFields(game: Game)
}