package `in`.rithikjain.thedolist.models

import `in`.rithikjain.thedolist.R
import androidx.glance.ImageProvider

data class Tab(
    val id: Int,
    val tabBackground: Int,
    val tabUnselectedBackground: Int,
    val tasksSelectedImage: Int,
    val tasksUnselectedImage: Int,
    val tasksColor: Long,
    val tasksBackgroundColor: Long,
)

val tabs = mapOf(
    1 to Tab(1,
        R.drawable.blue_circle,
        R.drawable.blue_unselected_circle,
        R.drawable.blue_selected,
        R.drawable.blue_unselected,
        0xFF1F51FF,
        0x121F51FF),
    2 to Tab(2,
        R.drawable.purple_circle,
        R.drawable.purple_unselected_circle,
        R.drawable.purple_selected,
        R.drawable.purple_unselected,
        0xFFab20fd,
        0x12ab20fd),
    3 to Tab(3,
        R.drawable.green_circle,
        R.drawable.green_unselected_circle,
        R.drawable.green_selected,
        R.drawable.green_unselected,
        0xFF39FF14,
        0x0839FF14),
    4 to Tab(4,
        R.drawable.red_circle,
        R.drawable.red_unselected_circle,
        R.drawable.red_selected,
        R.drawable.red_unselected,
        0xFFFF3131,
        0x08FF3131),
    5 to Tab(5,
        R.drawable.yellow_circle,
        R.drawable.yellow_unselected_circle,
        R.drawable.yellow_selected,
        R.drawable.yellow_unselected,
        0xFFFFFF00,
        0x08FFFF00),
)