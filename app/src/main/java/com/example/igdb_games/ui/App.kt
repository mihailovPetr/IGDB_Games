package com.example.igdb_games.ui

import android.app.Application
import com.example.igdb_games.di.AppComponent
import com.example.igdb_games.di.DaggerAppComponent
import com.example.igdb_games.di.ICompanyScopeContainer
import com.example.igdb_games.di.IGameScopeContainer
import com.example.igdb_games.di.company.CompanySubcomponent
import com.example.igdb_games.di.game.GameSubcomponent
import com.example.igdb_games.di.module.AppModule

class App : Application(), IGameScopeContainer, ICompanyScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var gameSubcomponent: GameSubcomponent? = null
        private set

    var companySubcomponent: CompanySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initGameSubcomponent() = appComponent.gameSubcomponent().also {
        gameSubcomponent = it
    }

    fun initCompanySubcomponent() = appComponent.companySubcomponent().also {
        companySubcomponent = it
    }

    override fun releaseGameScope() {
        gameSubcomponent = null
    }

    override fun releaseCompanyScope() {
        companySubcomponent = null
    }
}
