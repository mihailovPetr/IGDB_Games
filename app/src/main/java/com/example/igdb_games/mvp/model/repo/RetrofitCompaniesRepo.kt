package com.example.igdb_games.mvp.model.repo

import com.example.igdb_games.mvp.model.api.IDataSource
import com.example.igdb_games.mvp.model.cache.ICompaniesCache
import com.example.igdb_games.mvp.model.entity.*
import com.example.igdb_games.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class RetrofitCompaniesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: ICompaniesCache
) : ICompaniesRepo {

    private val involvedCompaniesRequestText =
        "fields company.name, developer, publisher; where game = %d;"

    override fun getInvolvedCompanies(game: Game): Single<List<InvolvedCompany>> {

        val requestBody = String.format(involvedCompaniesRequestText, game.id)
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getInvolvedCompanies(requestBody)
                    .flatMap { involvedCompanyDTOs ->
                        val involvedCompanies =
                            involvedCompanyDTOs.map { InvolvedCompany.fromInvolvedCompanyDTO(it) }
                        cache.putInvolvedCompanies(game, involvedCompanies)
                            .toSingleDefault(involvedCompanies)
                    }
            } else {
                cache.getInvolvedCompanies(game)
            }
        }.subscribeOn(Schedulers.io())

    }

    private val getCompanyRequestText =
        "fields name, description, developed.name, published.name, logo.image_id; where id = %d;"

    override fun getCompany(companyId: Int): Single<Company> {

        val requestBody = String.format(getCompanyRequestText, companyId)
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getCompany(requestBody)
                    .flatMap { companyDTO ->
                        val company = Company.fromCompanyDTO(companyDTO[0])
                        cache.putCompany(company)
                            .toSingleDefault(company)
                    }
            } else {
                cache.getCompany(companyId)
            }
        }.subscribeOn(Schedulers.io())
    }

}
