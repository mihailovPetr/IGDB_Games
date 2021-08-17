package com.example.igdb_games.mvp.model.entity.dto

data class CompanyDTO(
    val id: Int,
    val name: String?,
    val description: String?,
    val developed: List<GameDTO>?,
    val published: List<GameDTO>?
)