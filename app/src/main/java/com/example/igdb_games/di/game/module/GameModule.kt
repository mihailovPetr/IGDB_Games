package com.example.igdb_games.di.game.module

import com.example.igdb_games.di.IGameScopeContainer
import com.example.igdb_games.di.game.GameScope
import com.example.igdb_games.mvp.model.api.IDataSource
import com.example.igdb_games.mvp.model.cache.IGamesCache
import com.example.igdb_games.mvp.model.cache.room.RoomGamesCache
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.room.db.Database
import com.example.igdb_games.mvp.model.network.INetworkStatus
import com.example.igdb_games.mvp.model.repo.IGamesRepo
import com.example.igdb_games.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
open class GameModule {

    @Provides
    fun gamesCache(database: Database): IGamesCache {
        return RoomGamesCache(database)
    }

    @GameScope
    @Provides
    open fun gamesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGamesCache
    ): IGamesRepo {
        return FakeGamesRepo()
    }

    @GameScope
    @Provides
    open fun scopeContainer(app: App): IGameScopeContainer = app
}

class FakeGamesRepo() : IGamesRepo {

    override fun getGames() : Single<List<Game>>{
        val list: MutableList<Game> = mutableListOf()
        for (index in 1..100) {
            list.add(
                Game(index, "Name: $index", null,null,null, null)
            )
        }
        return Single.just(list.toList()).subscribeOn(Schedulers.io())
    }


    override fun getGame(gameId: Int): Single<Game> {
        val game = Game(-1, "game name", null, null, null, null)
        return Single.just(game).subscribeOn(Schedulers.io())
    }

}