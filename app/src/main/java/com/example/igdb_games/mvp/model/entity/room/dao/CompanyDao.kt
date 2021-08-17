package com.example.igdb_games.mvp.model.entity.room.dao

import androidx.room.*
import com.example.igdb_games.mvp.model.entity.room.RoomCompany
import com.example.igdb_games.mvp.model.entity.room.RoomGame

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(company: RoomCompany)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg comapnies: RoomCompany)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(companies: List<RoomCompany>)

    @Update
    fun update(company: RoomCompany)

    @Update
    fun update(vararg comapnies: RoomCompany)

    @Update
    fun update(companies: List<RoomCompany>)

    @Delete
    fun delete(company: RoomCompany)

    @Delete
    fun delete(vararg comapnies: RoomCompany)

    @Delete
    fun delete(companies: List<RoomCompany>)

    @Query("SELECT * FROM RoomCompany")
    fun getAll(): List<RoomCompany>

    @Query("SELECT * FROM RoomCompany WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomCompany?

    @Query("SELECT RoomCompany.* FROM RoomCompany, RoomGameCompanyCrossRef WHERE RoomCompany.id = RoomGameCompanyCrossRef.companyId AND RoomGameCompanyCrossRef.gameId == :gameId AND RoomGameCompanyCrossRef.developer == true")
    fun findDevelopers(gameId: Int): List<RoomCompany>

    @Query("SELECT RoomCompany.* FROM RoomCompany, RoomGameCompanyCrossRef WHERE RoomCompany.id = RoomGameCompanyCrossRef.companyId AND RoomGameCompanyCrossRef.gameId == :gameId AND RoomGameCompanyCrossRef.developer == true")
    fun findPublishers(gameId: Int): List<RoomCompany>
}
