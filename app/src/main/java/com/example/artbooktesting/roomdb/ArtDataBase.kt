package com.example.artbooktesting.roomdb

import androidx.room.Database
import androidx.room.Entity

@Database(entities = [Art::class], version = 1)
abstract class ArtDataBase {
abstract fun artDao(): ArtDao
}