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
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deletePGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGame()

    @Query("SELECT COUNT(win) FROM gameTable WHERE win= 1" )
    suspend fun getWins(): Int

    @Query("SELECT COUNT(draw) FROM gameTable WHERE draw =1 ")
    suspend fun getDraws(): Int

    @Query("SELECT COUNT(loss) FROM gameTable WHERE loss = 1" )
    suspend fun getLosses(): Int

}