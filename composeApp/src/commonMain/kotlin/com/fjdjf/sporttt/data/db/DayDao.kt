package com.fjdjf.sporttt.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {

    @Insert
    suspend fun addDay(day: Day)

    @Query("SELECT * FROM days")
    fun getAllDays() : Flow<List<Day>>
}