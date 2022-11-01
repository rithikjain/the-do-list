package `in`.rithikjain.thedolist.db

import `in`.rithikjain.thedolist.models.Task
import `in`.rithikjain.thedolist.utils.SingletonHolder
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 2,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "thedolist.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    })
}