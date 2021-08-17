package com.example.igdb_games.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RoomCompany(
    @PrimaryKey var id: Int,
    var name: String?,
    var description: String?
)