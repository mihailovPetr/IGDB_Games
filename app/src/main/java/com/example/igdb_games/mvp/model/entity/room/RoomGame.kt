package com.example.igdb_games.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGame(
    @PrimaryKey var id: Int,
    var name: String?,
    var summary: String?,
    var rating: Float?,
    var aggregatedRating: Float?
)
