package `in`.rithikjain.thedolist

import `in`.rithikjain.thedolist.ui.components.LinkText
import `in`.rithikjain.thedolist.ui.components.LinkTextData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.drawablepainter.rememberDrawablePainter

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(0xFF161616)
            ) {

                val uriHandler = LocalUriHandler.current

                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Image(
                            painter = rememberDrawablePainter(AppCompatResources.getDrawable(
                                LocalContext.current,
                                R.drawable.blue_circle,
                            )),
                            contentDescription = "Selected Box",
                            modifier = Modifier.size(24.dp),
                        )
                        UnselectedTab(R.drawable.purple_unselected_circle)
                        UnselectedTab(R.drawable.green_unselected_circle)
                        UnselectedTab(R.drawable.red_unselected_circle)
                        UnselectedTab(R.drawable.yellow_unselected_circle)
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x121F51FF))) {
                        LazyColumn(contentPadding = PaddingValues(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()) {
                            item {
                                Image(
                                    painter = rememberAsyncImagePainter(R.mipmap.ic_launcher_foreground),
                                    contentDescription = null,
                                    modifier = Modifier.size(150.dp)
                                )
                                Text("The NO BS to do list widget.",
                                    style = TextStyle(fontStyle = FontStyle.Italic,
                                        fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center,
                                    color = Color.White)
                                Spacer(Modifier.height(32.dp))
                                Divider(color = Color(0x4D1F51FF), thickness = 0.5.dp)
                                Spacer(Modifier.height(32.dp))
                                Text(
                                    "Add the widget",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    fontSize = 22.sp,
                                    color = Color.White,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    "Since this is just a widget, please add the widget to your home screen, not the app icon xD",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                )
                                Text(
                                    "Here's how:",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp)
                                )
                                Text(
                                    "1. On your home screen, long press anywhere on an area that is empty",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp)
                                )
                                Text(
                                    "2. Select the option 'Widgets'",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp)
                                )
                                Text(
                                    "3. Find the The Do List widget and drag it to wherever you want to place it",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp)
                                )
                                Text(
                                    "How do I use the widget?",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    fontSize = 22.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp)
                                )
                                Text(
                                    "It's super straightforward. Once you add the widget on your home screen, you'll figure it out on your own xD. But just to clarify the functionalities:",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp)
                                )
                                Text(
                                    "- Click the add button to add a task",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp)
                                )
                                Text(
                                    "- Clicking on a task will allow you to modify/delete it",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp)
                                )
                                Text(
                                    "- You can tap on the boxes next to task to toggle the completion of it",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp)
                                )

                                Spacer(Modifier.height(32.dp))
                                Divider(color = Color(0x4D1F51FF), thickness = 0.5.dp)
                                Spacer(Modifier.height(32.dp))

                                LinkText(
                                    linkTextData = listOf(
                                        LinkTextData(
                                            text = "This app is completely free to use and is open source. The source code can be found ",
                                        ),
                                        LinkTextData(
                                            text = "here. ",
                                            tag = "github_link",
                                            annotation = "https://github.com/rithikjain/the-do-list",
                                            onClick = {
                                                uriHandler.openUri(it.item)
                                            },
                                        ),
                                        LinkTextData(
                                            text = "If you liked the experience, please give this app a nice review on the play store 😊",
                                        ),
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                LinkText(
                                    linkTextData = listOf(
                                        LinkTextData(
                                            text = "In case of any bugs/feedback, kindly reach out to me: ",
                                        ),
                                        LinkTextData(
                                            text = "rithik.jain@3006@gmail.com",
                                            tag = "email_link",
                                            annotation = "mailto:rithik.jain3006@gmail.com",
                                            onClick = {
                                                uriHandler.openUri(it.item)
                                            },
                                        )
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                )

                                LinkText(
                                    linkTextData = listOf(
                                        LinkTextData(
                                            text = "Connect with me on ",
                                        ),
                                        LinkTextData(
                                            text = "LinkedIn",
                                            tag = "linkedin_link",
                                            annotation = "https://www.linkedin.com/in/rithik-jain-710b3a199/",
                                            onClick = {
                                                uriHandler.openUri(it.item)
                                            },
                                        )
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                )

                                Text(
                                    "Made with ❤ by Rithik Jain",
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(top = 72.dp, bottom = 12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun UnselectedTab(image: Int) {
        Image(
            painter = rememberDrawablePainter(AppCompatResources.getDrawable(
                LocalContext.current,
                image,
            )),
            contentDescription = "Task Unselected Box",
            modifier = Modifier.size(24.dp),
        )
    }
}