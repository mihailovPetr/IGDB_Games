package com.example.igdb_games.mvp.model.repo

import com.example.igdb_games.mvp.model.api.IDataSource
import com.example.igdb_games.mvp.model.cache.IGamesCache
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.InvolvedCompany
import com.example.igdb_games.mvp.model.entity.fromGameDTO
import com.example.igdb_games.mvp.model.entity.fromInvolvedCompanyDTO
import com.example.igdb_games.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class RetrofitGamesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGamesCache
) : IGamesRepo {

    private val gamesRequestBody =
        ("fields name, summary, rating, aggregated_rating; sort total_rating desc; " +
                "where aggregated_rating_count > 3 & rating_count > 20; limit 100;")
            .toRequestBody("text/plain".toMediaTypeOrNull())

    override fun getGames() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getGames(gamesRequestBody)
                .flatMap { gamesDTO ->
                    val games = gamesDTO.map { Game.fromGameDTO(it) }
                    cache.putGames(games).toSingleDefault(games)
                }
        } else {
            cache.getGames()
        }
    }.subscribeOn(Schedulers.io())


    private val gameRequestText = "fields name, summary, rating, aggregated_rating; where id = %d;"

    override fun getGame(gameId: Int): Single<Game> {

        val requestBody = String.format(gameRequestText, gameId)
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getGames(requestBody)
                    .flatMap { gameDTO ->
                        val game = Game.fromGameDTO(gameDTO[0])
                        cache.putGame(game)
                            .toSingleDefault(game)
                    }
            } else {
                cache.getGame(gameId)
            }
        }.subscribeOn(Schedulers.io())

    }

}