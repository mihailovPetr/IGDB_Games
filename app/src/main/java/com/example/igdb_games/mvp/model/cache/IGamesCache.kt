package com.example.igdb_games.mvp.model.cache

import com.example.igdb_games.mvp.model.entity.Game
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGamesCache {
    fun getGames(): Single<List<Game>>
    fun putGames(games: List<Game>): Completable
    fun putGame(game: Game): Completable
    fun getGame(gameId: Int): Single<Game>
}