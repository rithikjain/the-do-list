package `in`.rithikjain.thedolist.data

import `in`.rithikjain.thedolist.R
import androidx.glance.ImageProvider

data class Tab(
    val id: Int,
    val tabBackground: ImageProvider,
    val tabColor: Long,
)

val tabs = listOf(
    Tab(1, ImageProvider(R.drawable.blue_circle), 0x1A1F51FF),
    Tab(2, ImageProvider(R.drawable.purple_circle), 0x1Aab20fd),
    Tab(3, ImageProvider(R.drawable.green_circle), 0x0D39FF14),
    Tab(4, ImageProvider(R.drawable.red_circle), 0x26FF3131),
    Tab(5, ImageProvider(R.drawable.yellow_circle), 0x26FFFF00),
)