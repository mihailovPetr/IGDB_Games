package com.example.igdb_games.di.company

import com.example.igdb_games.di.company.module.CompanyModule
import com.example.igdb_games.mvp.presenter.CompanyPresenter
import com.example.igdb_games.mvp.presenter.GamePresenter
import dagger.Subcomponent

@CompanyScope
@Subcomponent(
    modules = [
        CompanyModule::class
    ]
)
interface CompanySubcomponent {
    fun inject(gamePresenter: GamePresenter)
    fun inject(companyPresenter: CompanyPresenter)
}