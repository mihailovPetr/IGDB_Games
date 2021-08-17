package com.example.igdb_games.mvp.model.entity.dto

data class InvolvedCompanyDTO(
    val company: CompanyDTO,
    val developer: Boolean,
    val publisher: Boolean
)