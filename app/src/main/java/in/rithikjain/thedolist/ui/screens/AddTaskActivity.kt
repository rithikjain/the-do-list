package `in`.rithikjain.thedolist.ui.screens

import `in`.rithikjain.thedolist.R
import `in`.rithikjain.thedolist.db.AppDatabase
import `in`.rithikjain.thedolist.models.Task
import `in`.rithikjain.thedolist.models.tabs
import `in`.rithikjain.thedolist.utils.Constants
import `in`.rithikjain.thedolist.widget.TheDoListWidget
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.launch

class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var tabID = -1

        intent?.extras?.let {
            tabID = it.getInt(Constants.SELECTED_TAB_ID_KEY)
        }

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {

                val drawable = AppCompatResources.getDrawable(
                    LocalContext.current,
                    tabs[tabID]!!.tasksUnselectedImage
                )

                val focusRequester = FocusRequester()

                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            finish()
                        })
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF161616))
                            .background(Color(tabs[tabID]!!.tasksBackgroundColor))
                            .padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 48.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberDrawablePainter(drawable = drawable),
                                contentDescription = "Task Unselected Box",
                                modifier = Modifier.size(18.dp),
                            )

                            var text by remember { mutableStateOf("") }

                            Box(modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp)) {
                                if (text.trim().isEmpty()) {
                                    Text("Add a task", fontSize = 15.sp, color = Color.LightGray)
                                }
                                BasicTextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    cursorBrush = SolidColor(Color.White),
                                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                                    modifier = Modifier.focusRequester(focusRequester)
                                )
                            }

                            Image(
                                painter = rememberDrawablePainter(
                                    AppCompatResources.getDrawable(
                                        LocalContext.current,
                                        R.drawable.ic_up
                                    )
                                ),
                                contentDescription = "Add Task Button",
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        insertTask(text.trim(), tabID)
                                    },
                                colorFilter = ColorFilter.tint(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun insertTask(content: String, tabID: Int) {
        val db = AppDatabase.getInstance(this)

        lifecycleScope.launch {
            db.tasksDao().insertTask(Task(content = content, tabID = tabID, isCompleted = false))
            val glanceID =
                GlanceAppWidgetManager(this@AddTaskActivity).getGlanceIds(TheDoListWidget::class.java)
                    .firstOrNull()
            if (glanceID != null) {
                TheDoListWidget().update(this@AddTaskActivity, glanceID)
            }
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            overridePendingTransition(0, 0)
        }
    }
}