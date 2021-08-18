package com.example.igdb_games.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.example.igdb_games.mvp.model.entity.room.RoomCompany
import com.example.igdb_games.mvp.model.entity.room.RoomGame
import com.example.igdb_games.mvp.model.entity.room.RoomGameCompanyCrossRef
import com.example.igdb_games.mvp.model.entity.room.dao.CompanyDao
import com.example.igdb_games.mvp.model.entity.room.dao.GameCompanyCrossRefDao
import com.example.igdb_games.mvp.model.entity.room.dao.GameDao

@androidx.room.Database(
    entities = [RoomGame::class, RoomCompany::class, RoomGameCompanyCrossRef::class],
    version = 3
)
abstract class Database : RoomDatabase() {
    abstract val gameDao: GameDao
    abstract val companyDao: CompanyDao
    abstract val gameCompanyCrossRefDao: GameCompanyCrossRefDao
}
