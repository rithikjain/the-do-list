package `in`.rithikjain.thedolist.widget.actions

import `in`.rithikjain.thedolist.widget.TheDoListWidget
import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState

class ChangeTabActionCallback : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val id = parameters[TheDoListWidget.PARAM_SELECTED_TAB_ID] ?: 1

        updateAppWidgetState(context, glanceId) {
            it[TheDoListWidget.PREF_SELECTED_TAB_ID] = id
        }

        TheDoListWidget().update(context, glanceId)
    }
}