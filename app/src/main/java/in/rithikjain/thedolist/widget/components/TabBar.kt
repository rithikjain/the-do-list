package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.data.tabs
import `in`.rithikjain.thedolist.widget.TheDoListWidget
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
        modifier = GlanceModifier
            .fillMaxWidth().height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            tabs.forEachIndexed { i, tab ->

                var modifier = GlanceModifier.size(22.dp)

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
                            .size(19.dp)
                            .background(tab.tabBackground)
                            .clickable(actionRunCallback<ChangeTabActionCallback>(
                                actionParametersOf(
                                    TheDoListWidget.PARAM_COLOR to tab.tabColor,
                                    TheDoListWidget.PARAM_SELECTED_ID to tab.id,
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
}