package com.example.igdb_games.mvp.model.api

import com.example.igdb_games.mvp.model.entity.dto.CompanyDTO
import com.example.igdb_games.mvp.model.entity.dto.GameDTO
import com.example.igdb_games.mvp.model.entity.dto.InvolvedCompanyDTO
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IDataSource {

    @Headers(
        "Client-ID: b398zvvmd1gwxtji4nnn0ajjjmdlct",
        "Authorization: Bearer 2ewuq0ptb17tq1pirw8ywygcc4x11d"
    )
    @POST("games")
    fun getGames(@Body body: RequestBody): Single<List<GameDTO>>

    @Headers(
        "Client-ID: b398zvvmd1gwxtji4nnn0ajjjmdlct",
        "Authorization: Bearer 2ewuq0ptb17tq1pirw8ywygcc4x11d"
    )
    @POST("involved_companies")
    fun getInvolvedCompanies(@Body body: RequestBody): Single<List<InvolvedCompanyDTO>>

    @Headers(
        "Client-ID: b398zvvmd1gwxtji4nnn0ajjjmdlct",
        "Authorization: Bearer 2ewuq0ptb17tq1pirw8ywygcc4x11d"
    )
    @POST("companies")
    fun getCompany(@Body body: RequestBody): Single<List<CompanyDTO>>

}
