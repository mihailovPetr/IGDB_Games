package com.example.igdb_games.mvp.model.entity

import android.os.Parcelable
import com.example.igdb_games.mvp.model.entity.dto.CompanyDTO
import com.example.igdb_games.mvp.model.entity.room.RoomCompany
import com.example.igdb_games.mvp.model.entity.room.RoomGame
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val id: Int,
    val name: String?,
    val description: String?,
    val developed: List<Game>?,
    val published: List<Game>?
) : Parcelable {
    companion object {}
}

fun Company.toRoomCompany(): RoomCompany {
    return RoomCompany(id, name, description)
}

fun Company.Companion.fromRoomCompany(
    roomCompany: RoomCompany,
    developed: List<RoomGame>? = null,
    published: List<RoomGame>? = null
): Company {
    return Company(
        roomCompany.id,
        roomCompany.name,
        roomCompany.description,
        developed?.map { Game.fromRoomGame(it) },
        published?.map { Game.fromRoomGame(it) }
    )
}

fun Company.Companion.fromCompanyDTO(companyDTO: CompanyDTO): Company {

    var developed: List<Game>? = null
    var published: List<Game>? = null

    companyDTO.developed?.apply {
        developed = map { Game.fromGameDTO(it) }
    }

    companyDTO.published?.apply {
        published = map { Game.fromGameDTO(it) }

    }

    return Company(companyDTO.id, companyDTO.name, companyDTO.description, developed, published)
}


