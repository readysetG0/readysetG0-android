package com.readysetgo.readysetgo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Trace::class], version = 1)
abstract class TraceDatabase : RoomDatabase() {
    abstract fun traceDao(): TraceDao

    companion object {
        private const val NAME = "traces.db"
        private var INSTANCE: TraceDatabase? = null

        fun getInstance(context: Context): TraceDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TraceDatabase::class.java,
                        NAME
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}