package com.fjdjf.sporttt.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("days")
data class Day (
    @PrimaryKey(true) val id: Int = 0,
    val date: String,
    val well: String,
    val water: String,
    val kcal: String,
    val burned: String,
)