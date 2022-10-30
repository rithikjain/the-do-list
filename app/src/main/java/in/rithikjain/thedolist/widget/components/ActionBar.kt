package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.widget.actions.AddTaskActionCallback
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*

@Composable
fun ActionBar(selectedTabId: Int) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Image(
            ImageProvider(R.drawable.ic_settings),
            contentDescription = "Settings Icon",
            modifier = GlanceModifier.size(22.dp),
        )
        Spacer(modifier = GlanceModifier.width(16.dp))
        Image(
            ImageProvider(R.drawable.ic_add),
            contentDescription = "Add Icon",
            modifier = GlanceModifier.size(22.dp).clickable(actionRunCallback<AddTaskActionCallback>()),
        )
    }
}