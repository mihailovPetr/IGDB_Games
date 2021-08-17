package com.example.igdb_games.mvp.model.navigation

import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun games(): Screen
    fun game(game: Game): Screen
    fun company(company: Company): Screen
}