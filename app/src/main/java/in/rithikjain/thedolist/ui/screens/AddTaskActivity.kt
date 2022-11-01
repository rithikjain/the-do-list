package `in`.rithikjain.thedolist.ui.screens

import `in`.rithikjain.thedolist.ui.screens.ui.theme.TheDoListTheme
import `in`.rithikjain.thedolist.utils.Constants
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var tabID = -1

        intent?.extras?.let {
            tabID = it.getInt(Constants.SELECTED_TAB_ID_KEY)
        }

        setContent {
            TheDoListTheme {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent) {
                    Text("Tab ID: $tabID")
                }
            }
        }
    }
}