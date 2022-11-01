package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.utils.Constants
import `in`.rithikjain.thedolist.widget.actions.SetTaskStatusActionCallback
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun TaskTile(
    taskID: Int,
    task: String,
    isCompleted: Boolean,
    selectedImage: ImageProvider,
    unselectedImage: ImageProvider,
) {
    Column(modifier = GlanceModifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isCompleted) SelectedBox(taskID, selectedImage) else UnSelectedBox(taskID,
                unselectedImage)
            Spacer(modifier = GlanceModifier.width(8.dp))
            Text(task, style = TextStyle(fontSize = 16.sp))
        }
    }
}

@Composable
fun SelectedBox(taskID: Int, selectedImage: ImageProvider) {
    Box(
        modifier = GlanceModifier
            .size(16.dp)
            .background(selectedImage)
            .clickable(setTaskStatus(taskID, false))
    ) {}
}

@Composable
fun UnSelectedBox(taskID: Int, unselectedImage: ImageProvider) {
    Box(
        modifier = GlanceModifier
            .size(16.dp)
            .background(unselectedImage)
            .clickable(setTaskStatus(taskID, true)),
        contentAlignment = Alignment.Center,
    ) {}
}

private fun setTaskStatus(taskID: Int, isTaskCompleted: Boolean) =
    actionRunCallback<SetTaskStatusActionCallback>(actionParametersOf(
        Constants.PARAM_TASK_ID to taskID,
        Constants.PARAM_IS_TASK_COMPLETED to isTaskCompleted,
    ))
