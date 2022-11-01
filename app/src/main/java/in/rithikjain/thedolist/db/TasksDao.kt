package `in`.rithikjain.thedolist.db

import `in`.rithikjain.thedolist.models.Task
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TasksDao {
    @Query("SELECT * FROM task WHERE tab_id=:tabID")
    fun getAllTasksByTabID(tabID: Int): List<Task>

    @Insert
    suspend fun insertTasks(tasks: List<Task>)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE uid=:taskID")
    suspend fun updateTaskStatus(taskID: Int, isCompleted: Boolean)
}