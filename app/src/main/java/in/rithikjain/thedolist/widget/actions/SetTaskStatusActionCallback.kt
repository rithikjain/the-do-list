package `in`.rithikjain.thedolist.widget.actions

import `in`.rithikjain.thedolist.db.AppDatabase
import `in`.rithikjain.thedolist.utils.Constants
import `in`.rithikjain.thedolist.widget.TheDoListWidget
import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState

class SetTaskStatusActionCallback : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val taskID = parameters[Constants.PARAM_TASK_ID] ?: -1
        val isCompleted = parameters[Constants.PARAM_IS_TASK_COMPLETED] ?: false

        val db = AppDatabase.getInstance(context)
        db.tasksDao().updateTaskStatus(taskID, isCompleted)

        TheDoListWidget().update(context, glanceId)
    }
}