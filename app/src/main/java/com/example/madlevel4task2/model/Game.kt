package com.example.madlevel4task2.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game (
    @ColumnInfo
    var userMove: String,

    @ColumnInfo
    var computerMove: String,

    @ColumnInfo
    var playDate: Date,

    @ColumnInfo
    var gameResult: String,

    @ColumnInfo
    var win: Boolean,

    @ColumnInfo
    var loss: Boolean,

    @ColumnInfo
    var draw: Boolean,

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

): Parcelable