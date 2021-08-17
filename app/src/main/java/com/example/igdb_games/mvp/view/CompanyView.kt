package com.example.igdb_games.mvp.view

import com.example.igdb_games.mvp.model.entity.Company
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CompanyView : MvpView {
    fun init()
    fun updateDevelopedList()
    fun updatePublishedList()
    fun fillFields(company: Company)
}
