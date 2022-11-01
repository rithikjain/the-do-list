package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.models.tabs
import `in`.rithikjain.thedolist.utils.Constants
import `in`.rithikjain.thedolist.widget.actions.ChangeTabActionCallback
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.*

@Composable
fun TabBar(
    selectedTabID: Int,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            tabs.entries.forEach { entry ->

                val tab = entry.value

                val tabBackground =
                    if (tab.id == selectedTabID) tab.tabBackground else tab.tabUnselectedBackground

                Box(
                    modifier = GlanceModifier
                        .size(22.dp)
                        .background(tabBackground)
                        .clickable(actionRunCallback<ChangeTabActionCallback>(
                            actionParametersOf(
                                Constants.PARAM_SELECTED_TAB_ID to tab.id,
                            )
                        ))
                ) {}

                if (entry.key != tabs.size) {
                    Spacer(modifier = GlanceModifier.width(18.dp))
                }
            }
        }
    }
}