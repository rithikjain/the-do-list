package `in`.rithikjain.thedolist.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun TaskTile(task: String, selectedColor: Long, unselectedImage: ImageProvider) {
    Column(modifier = GlanceModifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            UnSelectedBox(unselectedImage)
//            SelectedBox(selectedColor)
            Spacer(modifier = GlanceModifier.width(8.dp))
            Text(task, style = TextStyle(fontSize = 16.sp))
        }
    }
}

@Composable
fun SelectedBox(selectedColor: Long) {
    Box(modifier = GlanceModifier.size(16.dp).background(Color(selectedColor))) {}
}

@Composable
fun UnSelectedBox(unselectedImage: ImageProvider) {
    Box(
        modifier = GlanceModifier.size(16.dp).background(unselectedImage),
        contentAlignment = Alignment.Center,
    ) {}
}
