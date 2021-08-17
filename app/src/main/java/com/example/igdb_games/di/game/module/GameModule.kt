package com.example.igdb_games.di.game.module

import com.example.igdb_games.di.IGameScopeContainer
import com.example.igdb_games.di.game.GameScope
import com.example.igdb_games.mvp.model.cache.IGamesCache
import com.example.igdb_games.mvp.model.api.IDataSource
import com.example.igdb_games.mvp.model.cache.room.RoomGamesCache
import com.example.igdb_games.mvp.model.entity.room.db.Database
import com.example.igdb_games.mvp.model.network.INetworkStatus
import com.example.igdb_games.mvp.model.repo.IGamesRepo
import com.example.igdb_games.mvp.model.repo.RetrofitGamesRepo
import com.example.igdb_games.ui.App
import dagger.Module
import dagger.Provides

@Module
open class GameModule {

    @Provides
    fun gamesCache(database: Database): IGamesCache {
        return RoomGamesCache(database)
    }

    @GameScope
    @Provides
    open fun gamesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGamesCache): IGamesRepo {
        return RetrofitGamesRepo(api, networkStatus, cache)
    }

    @GameScope
    @Provides
    open fun scopeContainer(app: App): IGameScopeContainer = app
}