package com.example.igdb_games.mvp.model.cache.room

import com.example.igdb_games.mvp.model.cache.ICompaniesCache
import com.example.igdb_games.mvp.model.entity.*
import com.example.igdb_games.mvp.model.entity.room.RoomGameCompanyCrossRef
import com.example.igdb_games.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomCompaniesCache(val db: Database) : ICompaniesCache {

    override fun getInvolvedCompanies(game: Game) = Single.fromCallable {
        db.gameCompanyCrossRefDao.findInvolvedRoomCompaniesForGame(game.id)
            .map { InvolvedCompany.fromInvolvedRoomCompany(it) }
    }.subscribeOn(Schedulers.io())

    override fun putInvolvedCompanies(game: Game, companies: List<InvolvedCompany>) =
        Completable.fromAction {
            val roomCompanies = companies.map { it.toRoomCompany() }
            db.companyDao.insert(roomCompanies)

            val crossRefs = companies.map { it.toRoomGameCompanyCrossRef(game.id) }
            db.gameCompanyCrossRefDao.insert(crossRefs)
        }.subscribeOn(Schedulers.io())

    override fun getCompany(companyId: Int) = Single.fromCallable {
        val developed = db.gameDao.findDevelopedGames(companyId)
        val published = db.gameDao.findPublishedGames(companyId)
        db.companyDao.findById(companyId)?.let { Company.fromRoomCompany(it, developed, published) }
    }.subscribeOn(Schedulers.io())

    override fun putCompany(company: Company) = Completable.fromAction {
        db.companyDao.insert(company.toRoomCompany())

        if (company.developed != null && company.published != null) {

            val developed = company.developed.map { it.toRoomGame() }
            val published = company.published.map { it.toRoomGame() }

            db.gameDao.insert(developed)
            db.gameDao.insert(published)

            val crossRefs =
                developed.map { RoomGameCompanyCrossRef(it.id, company.id, true, false) }
                    .toMutableList()

            for (game in published) {
                var contains = false
                for (crossRef in crossRefs) {
                    if (game.id == crossRef.gameId) {
                        crossRef.publisher = true
                        contains = true
                        break
                    }
                }
                if (!contains) {
                    crossRefs.add(RoomGameCompanyCrossRef(game.id, company.id, false, true))
                }
            }

            db.gameCompanyCrossRefDao.insert(crossRefs)
        }
    }.subscribeOn(Schedulers.io())
}