package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.db.AppDatabase
import `in`.rithikjain.thedolist.models.Tab
import `in`.rithikjain.thedolist.models.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.*

@Composable
fun TasksSection(tab: Tab) {

    val db = AppDatabase.getInstance(LocalContext.current)
    val tasks = db.tasksDao().getAllTasksByTabID(tab.id)

    LazyColumn(modifier = GlanceModifier.fillMaxHeight()) {
        item {
            Box(modifier = GlanceModifier.height(10.dp)) {}
        }
        items(tasks) { task: Task ->
            TaskTile(
                task = task,
                selectedTabId = tab.id,
                selectedImage = ImageProvider(tab.tasksSelectedImage),
                unselectedImage = ImageProvider(tab.tasksUnselectedImage),
            )
        }
        item {
            Box(modifier = GlanceModifier.height(12.dp)) {}
        }
    }
}