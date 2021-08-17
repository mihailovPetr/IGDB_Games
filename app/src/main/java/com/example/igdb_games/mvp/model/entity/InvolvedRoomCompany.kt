package com.example.igdb_games.mvp.model.entity

import androidx.room.Embedded
import com.example.igdb_games.mvp.model.entity.room.RoomCompany

data class InvolvedRoomCompany(
    @Embedded val roomCompany: RoomCompany,
    val developer: Boolean,
    val publisher: Boolean
)