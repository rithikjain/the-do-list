package `in`.rithikjain.thedolist.widget

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.widget.components.TabBar
import `in`.rithikjain.thedolist.widget.components.TaskTile
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize

class TheDoListWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Single

    @Composable
    override fun Content() {

        val backgroundColor = currentState(PREF_COLOR) ?: 0x261F51FF
        val selectedTabID = currentState(PREF_SELECTED_ID) ?: 1

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ImageProvider(R.drawable.widget_background))
        ) {
            Column {
                TabBar(selectedTabID)
                Box(modifier = GlanceModifier.fillMaxSize().background(Color(backgroundColor))) {
                    LazyColumn {
                        items(100) { i ->
                            TaskTile(task = "Task $i")
                        }
                    }
                }
            }
        }
    }

    companion object {
        val PREF_COLOR = longPreferencesKey("color")
        val PARAM_COLOR = ActionParameters.Key<Long>("color")

        val PREF_SELECTED_ID = intPreferencesKey("selected_id")
        val PARAM_SELECTED_ID = ActionParameters.Key<Int>("selected_id")
    }
}

class TheDoListWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TheDoListWidget()
}