package com.example.igdb_games.di.module

import androidx.room.Room
import com.example.igdb_games.mvp.model.entity.room.db.Database
import com.example.igdb_games.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Named("databaseName")
    @Provides
    fun databaseName(): String = "database.db"

    @Singleton
    @Provides
    fun database(app: App, @Named("databaseName") databaseName: String): Database =
        Room.databaseBuilder(app, Database::class.java, databaseName).fallbackToDestructiveMigration().build()

}
