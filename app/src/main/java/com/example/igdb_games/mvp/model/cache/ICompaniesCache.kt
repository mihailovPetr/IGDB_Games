package com.example.igdb_games.mvp.model.cache

import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.InvolvedCompany
import com.example.igdb_games.mvp.model.entity.room.RoomCompany
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ICompaniesCache {
    fun getInvolvedCompanies(game: Game): Single<List<InvolvedCompany>>
    fun putInvolvedCompanies(game: Game, companies: List<InvolvedCompany>): Completable
    fun getCompany(companyId: Int): Single<Company>
    fun putCompany(company: Company): Completable
}