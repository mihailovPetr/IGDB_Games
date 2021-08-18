package com.example.igdb_games.mvp.model.entity.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.igdb_games.mvp.model.entity.Image

@Entity
class RoomGame(
    @PrimaryKey var id: Int,
    var name: String?,
    var summary: String?,
    var rating: Float?,
    var aggregatedRating: Float?,
    @Embedded var cover: Image?
)
