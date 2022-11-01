package `in`.rithikjain.thedolist.widget

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.data.tabs
import `in`.rithikjain.thedolist.widget.components.ActionBar
import `in`.rithikjain.thedolist.widget.components.TabBar
import `in`.rithikjain.thedolist.widget.components.TaskTile
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.*

class TheDoListWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Single

    @Composable
    override fun Content() {

        val selectedTabID = currentState(PREF_SELECTED_TAB_ID) ?: 1
        val tab = tabs[selectedTabID]!!

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ImageProvider(R.drawable.widget_background))
        ) {
            Column {
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TabBar(selectedTabID)
                    ActionBar(selectedTabID)
                }
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(Color(tab.tasksBackgroundColor))
                ) {
                    LazyColumn {
                        item {
                            Spacer(modifier = GlanceModifier.height(16.dp))
                        }
                        items(100) { i ->
                            TaskTile(task = "Task $i", tab.tasksColor, tab.tasksUnselectedImage)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val SELECTED_TAB_ID_KEY = "selected_tab_id"
        val PREF_SELECTED_TAB_ID = intPreferencesKey(SELECTED_TAB_ID_KEY)
        val PARAM_SELECTED_TAB_ID = ActionParameters.Key<Int>(SELECTED_TAB_ID_KEY)
    }
}

class TheDoListWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TheDoListWidget()
}