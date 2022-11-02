package `in`.rithikjain.thedolist.db

import `in`.rithikjain.thedolist.models.Task
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TasksDao {
    @Query("SELECT * FROM task WHERE tab_id=:tabID")
    fun getAllTasksByTabID(tabID: Int): List<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE uid=:taskID")
    suspend fun updateTaskStatus(taskID: Int, isCompleted: Boolean)

    @Query("UPDATE task SET content=:content WHERE uid=:taskID")
    suspend fun updateTask(taskID: Int, content: String)

    @Query("DELETE FROM task WHERE uid=:taskID")
    suspend fun deleteTask(taskID: Int)
}