package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.*
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDoa(): GameDao

    companion object {
        private const val DATABASE_NAME = "Game_List_Database"

        @Volatile
        private var GameRoomDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (GameRoomDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (GameRoomDatabaseInstance == null) {
                        GameRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,
                                GameRoomDatabase::class.java,
                                DATABASE_NAME)
                                .build()
                    }
                }
            }
            return GameRoomDatabaseInstance
        }
    }

}