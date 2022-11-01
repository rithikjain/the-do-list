package `in`.rithikjain.thedolist.data

import `in`.rithikjain.thedolist.R
import androidx.glance.ImageProvider

data class Tab(
    val id: Int,
    val tabBackground: ImageProvider,
    val tasksUnselectedImage: ImageProvider,
    val tasksColor: Long,
    val tasksBackgroundColor: Long,
)

val tabs = mapOf(
    1 to Tab(1,
        ImageProvider(R.drawable.blue_circle),
        ImageProvider(R.drawable.blue_unselected),
        0xFF1F51FF,
        0x121F51FF),
    2 to Tab(2,
        ImageProvider(R.drawable.purple_circle),
        ImageProvider(R.drawable.purple_unselected),
        0xFFab20fd,
        0x12ab20fd),
    3 to Tab(3,
        ImageProvider(R.drawable.green_circle),
        ImageProvider(R.drawable.green_unselected),
        0xFF39FF14,
        0x0839FF14),
    4 to Tab(4,
        ImageProvider(R.drawable.red_circle),
        ImageProvider(R.drawable.red_unselected),
        0xFFFF3131,
        0x08FF3131),
    5 to Tab(5,
        ImageProvider(R.drawable.yellow_circle),
        ImageProvider(R.drawable.yellow_unselected),
        0xFFFFFF00,
        0x08FFFF00),
)