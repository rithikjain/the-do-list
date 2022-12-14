package `in`.rithikjain.thedolist.widget.components

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.ui.screens.add_task.AddTaskActivity
import `in`.rithikjain.thedolist.ui.screens.settings.SettingsActivity
import `in`.rithikjain.thedolist.utils.Constants
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.layout.*

@Composable
fun ActionBar(selectedTabId: Int) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        // TODO: Add a settings page
//        Image(
//            ImageProvider(R.drawable.ic_settings),
//            contentDescription = "Settings Icon",
//            modifier = GlanceModifier.size(24.dp).clickable(actionStartActivity(
//                Intent(
//                    LocalContext.current,
//                    SettingsActivity::class.java
//                ).setFlags(
//                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
//                            and Intent.FLAG_ACTIVITY_NEW_TASK
//                            and Intent.FLAG_ACTIVITY_CLEAR_TASK
//                )
//            )),
//        )
//
//        Spacer(modifier = GlanceModifier.width(16.dp))

        Image(
            ImageProvider(R.drawable.ic_add),
            contentDescription = "Add Icon",
            modifier = GlanceModifier.size(24.dp).clickable(actionStartActivity(
                Intent(
                    LocalContext.current,
                    AddTaskActivity::class.java
                ).setFlags(
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                            and Intent.FLAG_ACTIVITY_NEW_TASK
                            and Intent.FLAG_ACTIVITY_CLEAR_TASK
                ),
                actionParametersOf(
                    Constants.PARAM_SELECTED_TAB_ID to selectedTabId
                )
            )),
        )
    }
}