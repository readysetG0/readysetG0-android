package com.readysetgo.readysetgo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TraceDao {
    @Query("SELECT * FROM trace WHERE travel_info_id = :travelInfoId")
    fun selectAllTrace(travelInfoId: Long): List<Trace>

    @Query("SELECT * FROM trace WHERE travel_info_id = :travelInfoId AND day = :day")
    fun selectTraceByDay(travelInfoId: Long, day: Int): List<Trace>

    @Insert
    fun insertTraceList(traceList: List<Trace>): List<Long>

    @Delete
    fun deleteTrace(trace: Trace)
}