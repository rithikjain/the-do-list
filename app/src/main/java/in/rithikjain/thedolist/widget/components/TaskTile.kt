package `in`.rithikjain.thedolist.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text

@Composable
fun TaskTile(task: String) {
    Column(modifier = GlanceModifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            task,
        )
        Spacer(modifier = GlanceModifier.height(16.dp))
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color(0x20FFFFFF))
        ) {}
    }
}