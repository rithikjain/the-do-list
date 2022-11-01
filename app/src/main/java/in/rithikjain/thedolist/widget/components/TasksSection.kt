package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.db.AppDatabase
import `in`.rithikjain.thedolist.models.Tab
import `in`.rithikjain.thedolist.models.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Spacer
import androidx.glance.layout.height

@Composable
fun TasksSection(tab: Tab) {

    val db = AppDatabase.getInstance(LocalContext.current)
    val tasks = db.tasksDao().getAllTasks()

    LazyColumn {
        item {
            Spacer(modifier = GlanceModifier.height(16.dp))
        }
        items(tasks) { task: Task ->
            TaskTile(
                taskID = task.uid,
                task = task.content,
                isCompleted = task.isCompleted,
                selectedImage = tab.tasksSelectedImage,
                unselectedImage = tab.tasksUnselectedImage,
            )
        }
    }
}