package `in`.rithikjain.thedolist.widget

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.data.Tab
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.*
import androidx.glance.state.PreferencesGlanceStateDefinition

class TheDoListWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override val sizeMode = SizeMode.Single

    @Composable
    override fun Content() {

        val backgroundColor = currentState(PREF_COLOR) ?: 0xBF293462

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
                    Row {
                        tabs.forEachIndexed { i, tab ->
                            Box(
                                modifier = GlanceModifier
                                    .size(20.dp)
                                    .background(tab.tabBackground)
                                    .clickable(actionRunCallback<ChangeTabActionCallback>(
                                        actionParametersOf(PARAM_COLOR to tab.tabColor)
                                    ))
                            ) {}
                            if (i != tabs.size - 1) {
                                Spacer(modifier = GlanceModifier.width(18.dp))
                            }
                        }
                    }
                }
                Box(modifier = GlanceModifier.fillMaxSize().background(Color(backgroundColor))){}
            }
        }
    }

    companion object {
        val tabs = listOf(
            Tab(ImageProvider(R.drawable.blue_circle), 0xBF293462),
            Tab(ImageProvider(R.drawable.indigo_circle), 0xBF2E0249),
            Tab(ImageProvider(R.drawable.navy_blue_circle), 0xBF143F6B),
            Tab(ImageProvider(R.drawable.purple_circle), 0xBF541690),
        )

        val PREF_COLOR = longPreferencesKey("color")
        val PARAM_COLOR = ActionParameters.Key<Long>("color")
    }
}

class ChangeTabActionCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val color = parameters[TheDoListWidget.PARAM_COLOR] ?: 0xBF293462
        updateAppWidgetState(context, glanceId) {
            it[TheDoListWidget.PREF_COLOR] = color
        }
        TheDoListWidget().update(context, glanceId)
    }
}

class TheDoListWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TheDoListWidget()
}