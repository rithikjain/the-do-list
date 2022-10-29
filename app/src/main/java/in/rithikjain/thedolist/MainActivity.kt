package `in`.rithikjain.thedolist

import `in`.rithikjain.thedolist.widget.TheDoListWidget
import `in`.rithikjain.thedolist.widget.TheDoListWidgetReceiver
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.google.android.glance.tools.viewer.GlanceSnapshot
import com.google.android.glance.tools.viewer.GlanceViewerActivity

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
class MainActivity : GlanceViewerActivity() {

    override suspend fun getGlanceSnapshot(
        receiver: Class<out GlanceAppWidgetReceiver>,
    ): GlanceSnapshot {
        return when (receiver) {
            TheDoListWidgetReceiver::class.java -> GlanceSnapshot(
                instance = TheDoListWidget(),
                state = mutablePreferencesOf(TheDoListWidget.PREF_COLOR to 0xBF293462)
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun getProviders() = listOf(TheDoListWidgetReceiver::class.java)
}