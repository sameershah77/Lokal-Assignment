package com.sameer.lokalassignment.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.Utils.JobsDao

@Database(entities = [JobEntity::class], version = 1)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobsDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getDatabase(context: Context): JobDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobDatabase::class.java,
                    "job_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
