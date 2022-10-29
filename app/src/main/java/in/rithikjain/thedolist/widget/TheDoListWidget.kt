package `in`.rithikjain.thedolist.widget

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.data.Tab
import `in`.rithikjain.thedolist.utils.conditional
import `in`.rithikjain.thedolist.widget.components.TaskTile
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.*
import androidx.glance.state.PreferencesGlanceStateDefinition

class TheDoListWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override val sizeMode = SizeMode.Single

    @Composable
    override fun Content() {

        val backgroundColor = currentState(PREF_COLOR) ?: 0xBF293462
        val selectedTabID = currentState(PREF_SELECTED_ID) ?: 1

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ImageProvider(R.drawable.widget_background))
        ) {
            Column {
                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth().height(40.dp)
                        .background(ImageProvider(R.drawable.black_top_round_12)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        tabs.forEachIndexed { i, tab ->

                            var modifier = GlanceModifier.size(24.dp)

                            modifier = if (tab.id == selectedTabID) {
                                modifier.then(GlanceModifier.background(ImageProvider(R.drawable.selected_circle)))
                            } else {
                                modifier.then(GlanceModifier.background(Color.Transparent))
                            }

                            Box(
                                modifier = modifier,
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(22.dp)
                                        .background(tab.tabBackground)
                                        .clickable(actionRunCallback<ChangeTabActionCallback>(
                                            actionParametersOf(
                                                PARAM_COLOR to tab.tabColor,
                                                PARAM_SELECTED_ID to tab.id,
                                            )
                                        ))
                                ) {}
                            }
                            if (i != tabs.size - 1) {
                                Spacer(modifier = GlanceModifier.width(18.dp))
                            }
                        }
                    }
                }
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
        val tabs = listOf(
            Tab(1, ImageProvider(R.drawable.blue_circle), 0xBF293462),
            Tab(2, ImageProvider(R.drawable.indigo_circle), 0xBF2E0249),
            Tab(3, ImageProvider(R.drawable.navy_blue_circle), 0xBF143F6B),
            Tab(4, ImageProvider(R.drawable.purple_circle), 0xBF541690),
        )

        val PREF_COLOR = longPreferencesKey("color")
        val PARAM_COLOR = ActionParameters.Key<Long>("color")

        val PREF_SELECTED_ID = intPreferencesKey("selected_id")
        val PARAM_SELECTED_ID = ActionParameters.Key<Int>("selected_id")
    }
}

class ChangeTabActionCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val color = parameters[TheDoListWidget.PARAM_COLOR] ?: 0xBF293462
        val id = parameters[TheDoListWidget.PARAM_SELECTED_ID] ?: 1

        updateAppWidgetState(context, glanceId) {
            it[TheDoListWidget.PREF_COLOR] = color
            it[TheDoListWidget.PREF_SELECTED_ID] = id
        }

        TheDoListWidget().update(context, glanceId)
    }
}

class TheDoListWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TheDoListWidget()
}