package com.example.igdb_games.di.company.module

import com.example.igdb_games.di.ICompanyScopeContainer
import dagger.Module
import dagger.Provides
import com.example.igdb_games.di.company.CompanyScope
import com.example.igdb_games.mvp.model.cache.ICompaniesCache
import com.example.igdb_games.mvp.model.api.IDataSource
import com.example.igdb_games.mvp.model.cache.room.RoomCompaniesCache
import com.example.igdb_games.mvp.model.entity.room.db.Database
import com.example.igdb_games.mvp.model.network.INetworkStatus
import com.example.igdb_games.mvp.model.repo.ICompaniesRepo
import com.example.igdb_games.mvp.model.repo.RetrofitCompaniesRepo
import com.example.igdb_games.ui.App


@Module
open class CompanyModule {

    @Provides
    fun companiesCache(database: Database): ICompaniesCache {
        return RoomCompaniesCache(database)
    }

    @CompanyScope
    @Provides
    fun companiesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: ICompaniesCache): ICompaniesRepo {
        return RetrofitCompaniesRepo(api, networkStatus, cache)
    }

    @CompanyScope
    @Provides
    open fun scopeContainer(app: App): ICompanyScopeContainer = app
}