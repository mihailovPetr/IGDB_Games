package com.example.igdb_games.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.igdb_games.mvp.model.entity.room.RoomGame

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg games: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(games: List<RoomGame>)

    @Update
    fun update(game: RoomGame)

    @Update
    fun update(vararg games: RoomGame)

    @Update
    fun update(games: List<RoomGame>)

    @Delete
    fun delete(game: RoomGame)

    @Delete
    fun delete(vararg games: RoomGame)

    @Delete
    fun delete(games: List<RoomGame>)

    @Query("SELECT * FROM RoomGame")
    fun getAll(): List<RoomGame>

    @Query("SELECT * FROM RoomGame WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomGame?

    @Query("SELECT RoomGame.* FROM RoomGame INNER JOIN RoomGameCompanyCrossRef ON RoomGame.id = RoomGameCompanyCrossRef.gameId WHERE RoomGameCompanyCrossRef.companyId = :companyId AND RoomGameCompanyCrossRef.developer == true")
    fun findDevelopedGames(companyId: Int): List<RoomGame>

    @Query("SELECT RoomGame.* FROM RoomGame INNER JOIN RoomGameCompanyCrossRef ON RoomGame.id = RoomGameCompanyCrossRef.gameId WHERE RoomGameCompanyCrossRef.companyId = :companyId AND RoomGameCompanyCrossRef.publisher == true")
    fun findPublishedGames(companyId: Int): List<RoomGame>

}
