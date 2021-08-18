package com.example.igdb_games.mvp.model.entity

import android.os.Parcelable
import com.example.igdb_games.mvp.model.entity.dto.GameDTO
import com.example.igdb_games.mvp.model.entity.room.RoomGame
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String?,
    val summary: String?,
    val rating: Float?,
    val aggregatedRating: Float?,
    val cover: Image?
) : Parcelable {
    companion object {}
}

fun Game.toRoomGame() = RoomGame(id, name, summary, rating, aggregatedRating, cover)

fun Game.Companion.fromRoomGame(roomGame: RoomGame) =
    Game(roomGame.id, roomGame.name, roomGame.summary, roomGame.rating, roomGame.aggregatedRating, roomGame.cover)

fun Game.Companion.fromGameDTO(gameDTO: GameDTO) =
    Game(gameDTO.id, gameDTO.name, gameDTO.summary, gameDTO.rating, gameDTO.aggregatedRating, gameDTO.cover)

//fun Game.Companion.fromGameDTO(gameDTO: GameDTO): Game {
//
//    var developers: MutableList<Company>? = null
//    var publishers: MutableList<Company>? = null
//
//    gameDTO.involvedCompanies?.apply {
//        developers = mutableListOf()
//        publishers = mutableListOf()
//
//        forEach {
//            val company = Company.fromCompanyDTO(it.company)
//            if (it.developer) developers!!.add(company)
//            if (it.publisher) publishers!!.add(company)
//        }
//    }
//
//    return Game(
//        gameDTO.id,
//        gameDTO.name,
//        gameDTO.summary,
//        gameDTO.rating,
//        gameDTO.aggregatedRating,
//        developers,
//        publishers
//    )
//}

//fun Game.Companion.fromRoomGame(
//    roomGame: RoomGame,
//    developers: List<RoomCompany>? = null,
//    publishers: List<RoomCompany>? = null
//): Game {
//    return Game(
//        roomGame.id,
//        roomGame.name,
//        roomGame.summary,
//        roomGame.rating,
//        roomGame.aggregatedRating,
//        developers?.map { Company.fromRoomCompany(it) },
//        publishers?.map { Company.fromRoomCompany(it) }
//    )
//}
