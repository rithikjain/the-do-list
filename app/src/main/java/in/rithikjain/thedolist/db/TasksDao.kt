package `in`.rithikjain.thedolist.db

import `in`.rithikjain.thedolist.models.Task
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TasksDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>

    @Insert
    suspend fun insertTasks(vararg tasks: Task)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE uid=:taskID")
    suspend fun updateTaskStatus(taskID: Int, isCompleted: Boolean)
}