package com.example.igdb_games.mvp.model.repo

import com.example.igdb_games.mvp.model.entity.Game
import io.reactivex.rxjava3.core.Single

interface IGamesRepo {
    fun getGames(): Single<List<Game>>
    fun getGame(gameId: Int): Single<Game>
}