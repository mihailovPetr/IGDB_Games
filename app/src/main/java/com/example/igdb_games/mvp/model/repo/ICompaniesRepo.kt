package com.example.igdb_games.mvp.model.repo

import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.InvolvedCompany
import io.reactivex.rxjava3.core.Single

interface ICompaniesRepo {
    fun getInvolvedCompanies(game: Game): Single<List<InvolvedCompany>>
    fun getCompany(companyId: Int): Single<Company>
}