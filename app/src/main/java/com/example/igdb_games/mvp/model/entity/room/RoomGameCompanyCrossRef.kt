package com.example.igdb_games.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["gameId", "companyId"],
    foreignKeys = [ForeignKey(
        entity = RoomGame::class,
        parentColumns = ["id"],
        childColumns = ["gameId"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = RoomCompany::class,
        parentColumns = ["id"],
        childColumns = ["companyId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGameCompanyCrossRef(
    val gameId: Int,
    val companyId: Int,
    var developer: Boolean,
    var publisher: Boolean
)