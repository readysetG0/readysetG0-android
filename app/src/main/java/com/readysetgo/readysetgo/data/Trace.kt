package com.readysetgo.readysetgo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Trace(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trace_id")
    val traceId: Long? = null,

    @ColumnInfo(name = "travel_info_id")
    val travelInfoId: Long,

    @ColumnInfo(name = "day")
    val day: Int,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,
)
