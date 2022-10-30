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
        val color = parameters[TheDoListWidget.PARAM_COLOR] ?: 0x1A1F51FF
        val id = parameters[TheDoListWidget.PARAM_SELECTED_ID] ?: 1

        updateAppWidgetState(context, glanceId) {
            it[TheDoListWidget.PREF_COLOR] = color
            it[TheDoListWidget.PREF_SELECTED_ID] = id
        }

        TheDoListWidget().update(context, glanceId)
    }
}