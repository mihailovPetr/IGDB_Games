package com.example.igdb_games.mvp.model.entity.dto

data class GameDTO(
    val id: Int,
    val name: String?,
    val summary: String?,
    val rating: Float?,
    val aggregatedRating: Float?
)