package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.database.GameRoomDatabase
import com.example.madlevel4task2.model.Game

class GameRespository(context: Context) {
    private var gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDoa()
    }
    suspend fun gameHistory(): List<Game>{
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game){
       return gameDao.insertGame(game)
    }

    suspend fun deleteHistory(){
        return gameDao.deleteAllGame()
    }

    suspend fun getWins(): Int{
        return gameDao.getWins()
    }

    suspend fun getDraws(): Int{
        return gameDao.getDraws()
    }

    suspend fun getLosses(): Int{
        return gameDao.getLosses()
    }
}