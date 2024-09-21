package com.readysetgo.readysetgo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TraceDao {
    @Query("SELECT * FROM trace")
    fun getAllTrace(): List<Trace>

    @Query("SELECT * FROM trace WHERE day = (:day)")
    fun getTraceByUserId(day: Int): Trace

    @Insert
    fun insertTrace(trace: Trace)

    @Delete
    fun deleteTrace(trace: Trace)
}