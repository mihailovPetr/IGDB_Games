package com.example.igdb_games.mvp.model.entity

import android.os.Parcelable
import com.example.igdb_games.mvp.model.entity.dto.InvolvedCompanyDTO
import com.example.igdb_games.mvp.model.entity.room.RoomCompany
import com.example.igdb_games.mvp.model.entity.room.RoomGameCompanyCrossRef
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvolvedCompany(
    val company: Company,
    val developer: Boolean,
    val publisher: Boolean
) : Parcelable {
    companion object
}

fun InvolvedCompany.Companion.fromInvolvedCompanyDTO(involvedCompanyDTO: InvolvedCompanyDTO) =
    InvolvedCompany(
        Company.fromCompanyDTO(involvedCompanyDTO.company),
        involvedCompanyDTO.developer,
        involvedCompanyDTO.publisher
    )

fun InvolvedCompany.Companion.fromInvolvedRoomCompany(involvedRoomCompany: InvolvedRoomCompany) =
    InvolvedCompany(
        Company.fromRoomCompany(involvedRoomCompany.roomCompany),
        involvedRoomCompany.developer,
        involvedRoomCompany.publisher
    )

fun InvolvedCompany.toRoomGameCompanyCrossRef(gameId: Int) =
    RoomGameCompanyCrossRef(gameId, company.id, developer, publisher)

fun InvolvedCompany.toRoomCompany() =
    RoomCompany(company.id, company.name, company.description, company.logo)