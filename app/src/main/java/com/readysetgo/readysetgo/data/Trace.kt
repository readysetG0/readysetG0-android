package com.readysetgo.readysetgo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trace(
    @PrimaryKey val day: Int,
    @ColumnInfo(name = "latitude") val latitude: String,
    @ColumnInfo(name = "longitude") val longitude: String,
)
