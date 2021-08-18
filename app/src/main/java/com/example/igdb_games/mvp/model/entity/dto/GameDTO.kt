package com.example.igdb_games.mvp.model.entity.dto

import com.example.igdb_games.mvp.model.entity.Image

data class GameDTO(
    val id: Int,
    val name: String?,
    val summary: String?,
    val rating: Float?,
    val aggregatedRating: Float?,
    val cover: Image?
)

