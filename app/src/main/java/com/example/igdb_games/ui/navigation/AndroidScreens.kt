package com.example.igdb_games.ui.navigation

import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.navigation.IScreens
import com.example.igdb_games.ui.fragment.CompanyFragment
import com.example.igdb_games.ui.fragment.GameFragment
import com.example.igdb_games.ui.fragment.GamesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun games() = FragmentScreen { GamesFragment.newInstance() }
    override fun game(game: Game) = FragmentScreen { GameFragment.newInstance(game) }
    override fun company(company: Company) = FragmentScreen { CompanyFragment.newInstance(company) }
}