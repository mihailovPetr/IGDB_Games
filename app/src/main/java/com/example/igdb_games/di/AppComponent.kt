package com.example.igdb_games.di

import com.example.igdb_games.di.company.CompanySubcomponent
import com.example.igdb_games.di.game.GameSubcomponent
import com.example.igdb_games.ui.activity.MainActivity
import com.example.igdb_games.di.module.ApiModule
import com.example.igdb_games.di.module.AppModule
import com.example.igdb_games.di.module.CiceroneModule
import com.example.igdb_games.di.module.DatabaseModule
import com.example.igdb_games.mvp.presenter.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    fun gameSubcomponent(): GameSubcomponent
    fun companySubcomponent(): CompanySubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}