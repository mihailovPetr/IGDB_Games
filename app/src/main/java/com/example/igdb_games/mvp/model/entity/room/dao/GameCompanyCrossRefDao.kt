package com.example.igdb_games.mvp.model.entity.room.dao

import androidx.room.*
import com.example.igdb_games.mvp.model.entity.InvolvedRoomCompany
import com.example.igdb_games.mvp.model.entity.room.RoomGameCompanyCrossRef

@Dao
abstract class GameCompanyCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(crossRef: RoomGameCompanyCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg crossRefs: RoomGameCompanyCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(crossRefs: List<RoomGameCompanyCrossRef>)

    @Update
    abstract fun update(crossRef: RoomGameCompanyCrossRef)

    @Update
    abstract fun update(vararg crossRefs: RoomGameCompanyCrossRef)

    @Update
    abstract fun update(crossRefs: List<RoomGameCompanyCrossRef>)

    @Delete
    abstract fun delete(crossRef: RoomGameCompanyCrossRef)

    @Delete
    abstract fun delete(vararg crossRefs: RoomGameCompanyCrossRef)

    @Delete
    abstract fun delete(crossRefs: List<RoomGameCompanyCrossRef>)

    @Query("SELECT RoomCompany.*, RoomGameCompanyCrossRef.developer, RoomGameCompanyCrossRef.publisher FROM RoomCompany INNER JOIN RoomGameCompanyCrossRef ON RoomCompany.id = RoomGameCompanyCrossRef.companyId WHERE RoomGameCompanyCrossRef.gameId == :gameId")
    abstract fun findInvolvedRoomCompaniesForGame(gameId: Int): List<InvolvedRoomCompany>

}
