package com.example.igdb_games.mvp.model.cache.room

import com.example.igdb_games.mvp.model.cache.IGamesCache
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.fromRoomGame
import com.example.igdb_games.mvp.model.entity.room.db.Database
import com.example.igdb_games.mvp.model.entity.toRoomGame
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGamesCache(val db: Database) : IGamesCache {

    override fun getGames() = Single.fromCallable {
        db.gameDao.getAll().map { Game.fromRoomGame(it) }
    }.subscribeOn(Schedulers.io())

    override fun putGames(games: List<Game>) = Completable.fromAction {
        val roomGames = games.map { it.toRoomGame() }
        db.gameDao.insert(roomGames)
    }.subscribeOn(Schedulers.io())

    override fun putGame(game: Game) = Completable.fromAction {
        db.gameDao.insert(game.toRoomGame())
    }.subscribeOn(Schedulers.io())

    override fun getGame(gameId: Int) = Single.fromCallable {
        db.gameDao.findById(gameId)?.let { Game.fromRoomGame(it) }
            ?: Game(0, null, null, null, null, null)
    }.subscribeOn(Schedulers.io())
}