package `in`.rithikjain.thedolist.widget

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.db.AppDatabase
import `in`.rithikjain.thedolist.models.tabs
import `in`.rithikjain.thedolist.utils.Constants
import `in`.rithikjain.thedolist.widget.components.ActionBar
import `in`.rithikjain.thedolist.widget.components.TabBar
import `in`.rithikjain.thedolist.widget.components.TaskTile
import `in`.rithikjain.thedolist.widget.components.TasksSection
import android.util.Log
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

        val selectedTabID = currentState(Constants.PREF_SELECTED_TAB_ID) ?: 1
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
                    TasksSection(tab)
                }
            }
        }
    }
}

class TheDoListWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TheDoListWidget()
}