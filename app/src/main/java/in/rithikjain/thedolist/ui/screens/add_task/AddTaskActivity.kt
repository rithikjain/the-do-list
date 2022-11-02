package `in`.rithikjain.thedolist.ui.screens.add_task

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : ComponentActivity() {

    private var tabID = -1
    private var isEditTask = false
    private var taskContent = ""
    private var taskID = -1
    private var isTaskCompleted = false
    private var taskCreatedAt: Long = 0

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.extras?.let {
            tabID = it.getInt(Constants.SELECTED_TAB_ID_KEY)
            isEditTask = it.getBoolean(Constants.IS_EDIT_TASK_KEY)
            it.getString(Constants.TASK_CONTENT_KEY)?.let { content ->
                taskContent = content
            }
            taskID = it.getInt(Constants.TASK_ID_KEY)
            isTaskCompleted = it.getBoolean(Constants.IS_TASK_COMPLETED_KEY)
            taskCreatedAt = it.getLong(Constants.TASK_CREATED_AT_KEY)
        }

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF161616))
                            .background(Color(tabs[tabID]!!.tasksBackgroundColor))
                            .padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 48.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberDrawablePainter(AppCompatResources.getDrawable(
                                    LocalContext.current,
                                    if (isTaskCompleted) {
                                        tabs[tabID]!!.tasksSelectedImage
                                    } else {
                                        tabs[tabID]!!.tasksUnselectedImage
                                    }
                                )),
                                contentDescription = "Task Unselected Box",
                                modifier = Modifier.size(18.dp),
                            )

                            val keyboardController = LocalSoftwareKeyboardController.current
                            var text by remember { mutableStateOf(taskContent) }

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
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(
                                        onDone = { keyboardController?.hide() }
                                    ),
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
                                        upsertTask(text.trim())
                                    },
                                colorFilter = ColorFilter.tint(Color.LightGray)
                            )
                        }

                        if (isEditTask) {
                            Divider(modifier = Modifier.padding(top = 18.dp),
                                color = Color.DarkGray,
                                thickness = 0.5.dp)

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            ) {
                                Image(
                                    painter = rememberDrawablePainter(
                                        AppCompatResources.getDrawable(
                                            LocalContext.current,
                                            R.drawable.ic_delete
                                        )
                                    ),
                                    contentDescription = "Delete Task Button",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            deleteTask()
                                        },
                                    colorFilter = ColorFilter.tint(Color.Gray)
                                )

                                Text(getFormattedDate(), fontSize = 14.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun upsertTask(content: String) {
        val db = AppDatabase.getInstance(this)

        if (content.isEmpty()) {
            finish()
        } else {
            lifecycleScope.launch {
                if (isEditTask) {
                    db.tasksDao().updateTask(taskID, content)
                } else {
                    db.tasksDao().insertTask(
                        Task(content = content, tabID = tabID, isCompleted = false)
                    )
                }

                val glanceID =
                    GlanceAppWidgetManager(this@AddTaskActivity).getGlanceIds(TheDoListWidget::class.java)
                        .firstOrNull()
                if (glanceID != null) {
                    TheDoListWidget().update(this@AddTaskActivity, glanceID)
                }
                finish()
            }
        }
    }

    private fun deleteTask() {
        val db = AppDatabase.getInstance(this)

        lifecycleScope.launch {
            db.tasksDao().deleteTask(taskID)
            val glanceID =
                GlanceAppWidgetManager(this@AddTaskActivity).getGlanceIds(TheDoListWidget::class.java)
                    .firstOrNull()
            if (glanceID != null) {
                TheDoListWidget().update(this@AddTaskActivity, glanceID)
            }
            finish()
        }
    }

    private fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val datetime = Date(taskCreatedAt)
        return "Created on ${dateFormat.format(datetime)}"
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            overridePendingTransition(0, 0)
        }
    }
}