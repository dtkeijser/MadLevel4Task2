package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(product: Game)

    @Delete
    suspend fun deletePGame(product: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGame()

    @Query("SELECT COUNT(win) FROM gameTable")
    suspend fun getWins(): Int

    @Query("SELECT COUNT(draw) FROM gameTable")
    suspend fun getDraws(): Int

    @Query("SELECT COUNT(loss) FROM gameTable")
    suspend fun getLosses(): Int

}